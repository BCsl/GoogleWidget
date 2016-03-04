package github.hellocsl.example.googlewidget.adapter;

import android.graphics.Color;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.target.ImageViewTarget;

import java.util.List;

import github.hellocsl.example.googlewidget.BuildConfig;
import github.hellocsl.example.googlewidget.R;
import github.hellocsl.example.googlewidget.config.PaletteBitmap;
import github.hellocsl.example.googlewidget.config.PaletteBitmapTranscoder;

/**
 * Created by HelloCsl(cslgogogo@gmail.com) on 2016/2/29 0029.
 */
public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ViewHolder> implements View.OnClickListener {

    private List<String> items;
    private OnItemClickListener mOnItemClickListener;

    public ImageAdapter(List<String> items) {
        this.items = items;
    }

    public ImageAdapter setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
        return this;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_image, parent, false);
        v.setOnClickListener(this);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        String url = items.get(position);
        holder.text.setText("item " + position);
        holder.text.setBackgroundColor(Color.BLACK);
//        Glide.with(holder.imageView.getContext()).load(url).fitCenter().crossFade().diskCacheStrategy(DiskCacheStrategy.ALL).into(holder.imageView);
        Glide.with(holder.imageView.getContext()).load(url).asBitmap().transcode(new PaletteBitmapTranscoder(holder.imageView.getContext()), PaletteBitmap.class).
                fitCenter().diskCacheStrategy(DiskCacheStrategy.ALL).into(new ImageViewTarget<PaletteBitmap>(holder.imageView) {
            @Override
            protected void setResource(PaletteBitmap resource) {
                super.view.setImageBitmap(resource.bitmap);
                Palette.Swatch vibrantSwatch = resource.palette.getMutedSwatch();
                if (vibrantSwatch != null) {
                    holder.text.setTextColor(vibrantSwatch.getTitleTextColor());
                    holder.text.setBackgroundColor(vibrantSwatch.getRgb());
                }
            }
        });
        holder.itemView.setTag(position);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public void onClick(final View v) {
        if (mOnItemClickListener != null) {
            mOnItemClickListener.onItemClick(v, (int) v.getTag());
        }
    }

    protected static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView text;
        public ImageView imageView;

        public ViewHolder(View itemView) {
            super(itemView);
            text = (TextView) itemView.findViewById(R.id.item_text);
            imageView = (ImageView) itemView.findViewById(R.id.item_image);
        }
    }

    @Override
    public void onViewRecycled(ViewHolder holder) {
        super.onViewRecycled(holder);
        // optional, but recommended way to clear up the resources used by Glide
        Glide.clear(holder.imageView);
    }

    public interface OnItemClickListener {

        void onItemClick(View view, int position);

    }
}
