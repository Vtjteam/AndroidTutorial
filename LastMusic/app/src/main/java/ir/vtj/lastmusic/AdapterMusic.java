package ir.vtj.lastmusic;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

/**
 * Created by hassan on 1/21/17.
 */
public class AdapterMusic extends RecyclerView.Adapter<AdapterMusic.MainHolder> {
    private List<songArrayList> songArrayLists;
    private Context context;


    public AdapterMusic(List<songArrayList> songArrayLists, Context context) {
        this.songArrayLists = songArrayLists;
        this.context = context;
    }

    @Override
    public int getItemCount() {
        return songArrayLists.size();
    }

    @Override
    public void onBindViewHolder(final MainHolder mainHolder, int position) {
        final songArrayList songArrayList = songArrayLists.get(position);

        if (songArrayList.getThisTitle().equals("")) {
            mainHolder.title.setText("no name");
        } else {
            mainHolder.title.setText(songArrayList.getThisTitle());
        }
        if (songArrayList.getThisalbumId()==0) {
            mainHolder.album_id.setText("no album id");
        } else {
            mainHolder.album_id.setText(songArrayList.getThisalbumId() + "");
        }

        mainHolder.rel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MediaPlayer
                mpintro = MediaPlayer.create(context, Uri.parse(songArrayList.getThisdata()));
                mpintro.setLooping(true);
                mpintro.start();
            }
        });
    }

    @Override
    public MainHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.
                from(viewGroup.getContext()).
                inflate(R.layout.view_music, viewGroup, false);

        return new MainHolder(itemView);
    }

    public static class MainHolder extends RecyclerView.ViewHolder {

        protected TextView title,album_id;
        protected RelativeLayout rel;

        public MainHolder(View v) {
            super(v);
            title = (TextView) v.findViewById(R.id.title);
            album_id = (TextView) v.findViewById(R.id.album_id);
            rel = (RelativeLayout) v.findViewById(R.id.rel);

        }

    }

}
