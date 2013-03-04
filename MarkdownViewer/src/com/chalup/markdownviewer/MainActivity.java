/*
 * Copyright (C) 2012 Jerzy Chalupski
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.chalup.markdownviewer;

import com.actionbarsherlock.app.SherlockActivity;
import com.commonsware.cwac.anddown.AndDown;
import com.google.common.base.Charsets;
import com.google.common.io.CharStreams;

import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;

import java.io.InputStreamReader;

public class MainActivity extends SherlockActivity {

  private static final String TAG = "MarkdownViewer";

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    try {
      String markdown = CharStreams.toString(new InputStreamReader(getContentResolver().openInputStream(getIntent().getData()), Charsets.UTF_8));
      String html = new AndDown().markdownToHtml(markdown);
      ((WebView) findViewById(R.id.webView)).loadDataWithBaseURL(getIntent().getDataString(), html, "text/html", null, null);
    } catch (Exception e) {
      Log.e(TAG, "File read error: " + e);
    }

    getSupportActionBar().setTitle(getIntent().getData().getLastPathSegment());
  }

}
