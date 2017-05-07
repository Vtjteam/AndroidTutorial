package ir.vtj.lastmusic;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.provider.MediaStore;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by hassan on 5/7/17.
 */

public class GetMusic extends AsyncTask<Void,Void,ArrayList<songArrayList>> {

    private int titleColumn,artistColumn,idColumn,albumId;
    private long thisId,thisalbumId;
    private String data,thisTitle,thisArtist;
    private ArrayList<songArrayList> songArrayLists = new ArrayList<>();
    private Context context;
    private delegate delegate;

    public GetMusic(Context context,delegate delegate) {
        this.context = context;
        this.delegate = delegate;
    }

    interface delegate {
        void getMdata(ArrayList<songArrayList> cursor);
    }

    @Override
    protected void onPostExecute(ArrayList<songArrayList> cursor) {
        super.onPostExecute(cursor);
        delegate.getMdata(cursor);
    }

    @Override
    protected ArrayList<songArrayList> doInBackground(Void... params) {

        ContentResolver contentResolver = context.getContentResolver();
        Uri mediaUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        Log.wtf("SKJDBKJ", mediaUri.toString());
        Cursor mediaCursor = contentResolver.query(mediaUri, null, null, null, null);

        // if the cursor is null.
        if(mediaCursor != null && mediaCursor.moveToFirst())
        {
            Log.wtf("DSJK", "entered cursor");
            //get Columns
            // Store the title, id and artist name in Song Array list.
            do
            {
                titleColumn = mediaCursor.getColumnIndex(MediaStore.Audio.Media.TITLE);
                idColumn = mediaCursor.getColumnIndex(MediaStore.Audio.Media._ID);
                artistColumn = mediaCursor.getColumnIndex(MediaStore.Audio.Media.ARTIST);
                albumId = mediaCursor.getColumnIndex(MediaStore.Audio.Media.ALBUM_ID);
                int colData = mediaCursor.getColumnIndex(MediaStore.Audio.Media.DATA);

                thisId = mediaCursor.getLong(idColumn);
                thisalbumId = mediaCursor.getLong(albumId);
                thisTitle = mediaCursor.getString(titleColumn);
                thisArtist = mediaCursor.getString(artistColumn);
                data = mediaCursor.getString(colData);
                Log.e("size",data);

                // Add the info to our array.
//                if(this.albumId == thisalbumId)
//                {
                    Log.wtf("SAME2SAME", String.valueOf(thisalbumId));
                    Log.wtf("SAME2SAME", String.valueOf(this.albumId));
                    songArrayLists.add(new songArrayList(thisId,albumId,thisTitle,thisArtist,data));
//                }
            }
            while (mediaCursor.moveToNext());

            // For best practices, close the cursor after use.
            mediaCursor.close();
        }
        return songArrayLists;
    }
}
