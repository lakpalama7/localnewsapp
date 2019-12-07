package com.mca.global.news.fragments;


import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.mca.global.news.NewsDetailsActivity;
import com.mca.global.news.NewsModel;
import com.mca.global.news.R;
import com.mca.global.news.adapter.NewsRecyclerViewAdapter;


import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class TechnolgoyFragment extends Fragment {

    RecyclerView recyclerView;

    ArrayList<NewsModel> model;

    private NewsRecyclerViewAdapter recyclerViewAdapter;

    public TechnolgoyFragment() {
        // Required empty public constructor
        model = new ArrayList<>();
    }


    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_technolgoy, container, false);
        recyclerView = view.findViewById(R.id.recyclerview_news);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);

        Bundle bundle = getArguments();

        int newsMainCategory = bundle.getInt("newsMainCategory");
        int childPosition = bundle.getInt("childPosition");

        callSpecificApi(newsMainCategory, childPosition);
        return view;
    }

    private void callSpecificApi(int newsMainCategory, int childPosition) {

        switch (newsMainCategory) {
            case 0:
                switch (childPosition) {
                    case 0:
                        new ProcessInBackgroud().execute("https://www.apherald.com/rss/technologytel");
                        break;
                    case 1:
                        new ProcessInBackgroud().execute("https://www.apherald.com/rss/educationtel");
                        break;
                    case 2:
                        new ProcessInBackgroud().execute("https://www.apherald.com/rss/businesstel");
                        break;
                    case 3:
                        new ProcessInBackgroud().execute("https://www.apherald.com/rss/movieshollywoodtel");
                        break;
                    case 4:
                        new ProcessInBackgroud().execute("https://www.apherald.com/rss/healthtel");
                        break;
                    case 5:
                        new ProcessInBackgroud().execute("https://www.apherald.com/rss/sportstel");
                        break;
                }
                break;
            case 1:
                switch (childPosition) {
                    case 0:

                       // new ProcessInBackgroud().execute("https://tamil.samayam.com/news/rssfeedsection/48237907.cms");
                        new ProcessInBackgroud().execute("https://www.apherald.com/rss/tamilnadupoliticstel");
                        break;
                }
                break;
            case 2:
                switch (childPosition){
                    case 0:
                        new ProcessInBackgroud().execute("https://www.apherald.com/rss/indiapoliticstel");
                        break;
                }
                break;
            case 3:
                switch (childPosition){
                case 0:
                    new ProcessInBackgroud().execute("https://www.apherald.com/rss/worldpoliticstel");
                    break;
            }
            break;
        }

    }

    public InputStream getInputStream(URL url) {
        try {
            return url.openConnection().getInputStream();
        } catch (IOException e) {
            Log.d("error ", e.getMessage());
            return null;
        }
    }


    public class ProcessInBackgroud extends AsyncTask<String, Void, Exception> {

        ProgressDialog dialog = new ProgressDialog(getContext());
        Exception exception = null;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog.setMessage("Opening sites...");
            dialog.show();
        }


        @Override
        protected Exception doInBackground(String... newsurl) {
            Log.d("url 1 : ", newsurl[0]);

            try {
                URL url = new URL(newsurl[0]);
               // Log.v("url ",url.toString());
                XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
                factory.setNamespaceAware(false);
               // Log.d("hi ","hi");

                XmlPullParser parser = factory.newPullParser();
                parser.setInput(getInputStream(url), null);

                boolean insideItem = false;

                int eventType = parser.getEventType();
                NewsModel newsModel = null;
                while (eventType != XmlPullParser.END_DOCUMENT) {


                    if (eventType == XmlPullParser.START_TAG) {

                        if (parser.getName().equalsIgnoreCase("item")) {
                            insideItem = true;
                            newsModel = new NewsModel();
                        } else if (parser.getName().equalsIgnoreCase("title")) {
                            if (insideItem) {
                                //title.add(parser.nextText());
                                newsModel.setTitle(parser.nextText());
                            }
                        } else if (parser.getName().equalsIgnoreCase("image")) {
                            if (insideItem) {
                                // image.add(parser.nextText());
                                newsModel.setImageUrl(parser.nextText());
                            }
                        } else if (parser.getName().equalsIgnoreCase("publishDate")) {
                            if (insideItem) {
                                //publishdate.add(parser.nextText());
                                newsModel.setPublishdate(parser.nextText());
                            }
                        } else if (parser.getName().equalsIgnoreCase("summary")) {
                            if (insideItem) {
                                //summary.add(parser.nextText());
                                newsModel.setSummary(parser.nextText());
                            }
                        } else if (parser.getName().equalsIgnoreCase("WebLink")) {
                            if (insideItem) {
                                newsModel.setWeblink(parser.nextText());
                            }
                        } else if (parser.getName().equalsIgnoreCase("link")) {
                            if (insideItem) {
                                newsModel.setWeblink(parser.nextText());
                            }
                        } else if (parser.getName().equalsIgnoreCase("description")) {
                            if (insideItem) {
                                newsModel.setSummary(parser.nextText());
                            }
                        }
                    } else if (eventType == XmlPullParser.END_TAG && parser.getName().equalsIgnoreCase("item")) {
                        model.add(newsModel);
                        insideItem = false;
                    }
                    eventType = parser.next();

                }
            } catch (MalformedURLException e) {
                exception = e;
            } catch (XmlPullParserException e) {
                exception = e;
            } catch (IOException e) {
                exception = e;
            }
            return exception;
        }

        @Override
        protected void onPostExecute(Exception s) {
            super.onPostExecute(s);
            recyclerViewAdapter = new NewsRecyclerViewAdapter(getContext(), model);
            recyclerView.setAdapter(recyclerViewAdapter);
            recyclerViewAdapter.notifyDataSetChanged();
            dialog.dismiss();
            selectItemFromRecyclerView();
        }
    }

    private void selectItemFromRecyclerView() {
        //interface method call from Adapter view;
        recyclerViewAdapter.setOnItemClickListener(new NewsRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(getContext(), NewsDetailsActivity.class);
                intent.putExtra("url", model.get(position).getWeblink());
                intent.putExtra("title", model.get(position).getTitle());
                startActivity(intent);

                  /* Intent intent=new Intent(Intent.ACTION_VIEW);
                   intent.setData(Uri.parse(model.get(position).getWeblink()));
                   startActivity(intent);*/
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

    }
}



