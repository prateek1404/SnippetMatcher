package com.example.foodapp;

import android.app.Activity;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import org.apache.http.NameValuePair;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

public class MyReview extends Fragment implements OnClickListener {

	Button endPost;
	TextView voteCount, starValue, title;

	public MyReview() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
				.permitAll().build();
		StrictMode.setThreadPolicy(policy);
		DBConnector dbc = new DBConnector();
		String[] str = new String[3];

		str[0] = "Title";
		str[1] = "Rating";
		str[2] = "Votes";

		String[][] str1 = dbc.select(str);

		// ArrayList<NameValuePair> loginValuePairs = new
		// ArrayList<NameValuePair>();
		//dbc.setURL("http://128.199.166.144/test/grab_review.php");
		// dbc.setValuePairList(loginValuePairs);
		// dbc.addValuePair("username","zh");

		View rootView = inflater.inflate(R.layout.testpost, container, false);

		LinearLayout postlayout = (LinearLayout) rootView
				.findViewById(R.id.testpost);
		//
		for (int i = 0; i < 10; i++) {

			View view = getActivity().getLayoutInflater().inflate(
					R.layout.posts, postlayout, false);

			postlayout.addView(view);
			voteCount = (TextView) getActivity().findViewById(R.id.voteCount);
			starValue = (TextView) getActivity().findViewById(
					R.id.starVoteValue);
			title = (TextView) getActivity().findViewById(R.id.postTitle);
			title.setText(str1[0][0]);
			starValue.setText(str1[0][1]);
			voteCount.setText(str1[0][2]);

			if (i == 9) {

				View view1 = getActivity().getLayoutInflater().inflate(
						R.layout.endpost, postlayout, false);

				postlayout.addView(view1);

				endPost = (Button) rootView.findViewById(R.id.endPostButton);
				endPost.setOnClickListener(this);

			}

		}
		return rootView;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub

		int id = v.getId();
		if (id == R.id.endPostButton) {
			LinearLayout postlayout = (LinearLayout) getActivity()
					.findViewById(R.id.testpost);
			for (int i = 0; i < 10; i++) {

				View view = getActivity().getLayoutInflater().inflate(
						R.layout.posts, postlayout, false);

				postlayout.addView(view);

				if (i == 9) {

					View view1 = getActivity().getLayoutInflater().inflate(
							R.layout.endpost, postlayout, false);

					postlayout.addView(view1);
					endPost = (Button) getActivity().findViewById(
							R.id.endPostButton);
					endPost.setOnClickListener(this);

				}

			}

		}
	}

}
