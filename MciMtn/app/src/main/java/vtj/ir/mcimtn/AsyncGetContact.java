package vtj.ir.mcimtn;

import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.os.AsyncTask;
import android.provider.ContactsContract;

import java.util.ArrayList;

/**
 * Created by javadroid on 5/7/17.
 */

public class AsyncGetContact extends AsyncTask<Void, Void, ArrayList<ModelContact>> {
    private delegate dele;
    private Context context;
    ArrayList<ModelContact> Allcontacts = new ArrayList<>();
    ProgressDialog progressDialog;

    public AsyncGetContact(Context context, delegate d) {
        this.dele = d;
        this.context = context;
        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("لطفا صبر کنید...");
        progressDialog.show();
    }

    interface delegate {
        void endLoad(ArrayList<ModelContact> contacts);
    }

    @Override
    protected void onPostExecute(ArrayList<ModelContact> data) {
        if (progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
        dele.endLoad(data);
        super.onPostExecute(data);


    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

    }

    @Override
    protected ArrayList<ModelContact> doInBackground(Void... voids) {
        ContentResolver cr = this.context.getContentResolver();
        Cursor cursor = cr.query(ContactsContract.Contacts.CONTENT_URI,
                null, null, null, null);

        if (cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                String id = cursor.getString(
                        cursor.getColumnIndex(ContactsContract.Contacts._ID));
                String name = cursor.getString(cursor.getColumnIndex(
                        ContactsContract.Contacts.DISPLAY_NAME));
                if (cursor.getInt(cursor.getColumnIndex(
                        ContactsContract.Contacts.HAS_PHONE_NUMBER)) > 0) {
                    Cursor pCur = cr.query(
                            ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                            null,
                            ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?",
                            new String[]{id}, null);
                    while (pCur.moveToNext()) {
                        String phoneNo = pCur.getString(pCur.getColumnIndex(
                                ContactsContract.CommonDataKinds.Phone.NUMBER));
                        ModelContact contact = new ModelContact(name, phoneNo);
                        String base = phoneNo.replace(" ", "");
                        if (base.startsWith("093") || base.startsWith("+9893"))
                            contact.isMtn = true;
                        if (base.startsWith("091") || base.startsWith("+9891"))
                            contact.isMtn = false;

                        Allcontacts.add(contact);
                    }
                    pCur.close();
                }
            }
        } else {
            Utile.Log("No Any Contact!");
        }

        return Allcontacts;
    }


}
