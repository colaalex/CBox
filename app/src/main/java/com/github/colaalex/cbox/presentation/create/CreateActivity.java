package com.github.colaalex.cbox.presentation.create;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.github.colaalex.cbox.App;
import com.github.colaalex.cbox.R;
import com.github.colaalex.cbox.domain.entity.Post;

import javax.inject.Inject;
import javax.inject.Provider;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CreateActivity extends MvpAppCompatActivity implements CreateView {

    @InjectPresenter
    CreatePresenter presenter;

    @Inject
    Provider<CreatePresenter> presenterProvider;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.etPost)
    EditText editPost;
    @BindView(R.id.etTitle)
    EditText editTitle;

    @ProvidePresenter
    CreatePresenter providePresenter() {return presenterProvider.get();}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        App.getAppComponent().inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }

    @Override
    public void onBackPressed() {
        setResult(RESULT_CANCELED);
        super.onBackPressed();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.miSend) {
            presenter.sendPost(editTitle.getText().toString(), editPost.getText().toString());
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.create_menu, menu);
        return true;
    }

    @Override
    public void postAdded(Post post) {
        Intent intent = new Intent();
        intent.putExtra("Post", post);
        setResult(RESULT_OK);
        finish();
    }

    @Override
    public void addFailed() {
        setResult(2);
        finish();
    }

}
