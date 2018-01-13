package com.example.firebasegooglesignin;


import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.GoogleAuthProvider;

public class GoogleSignInPresenter {
    public static final String TAG = "GoogleSignInPresenter";

    private GoogleSignInView googleSignInView;
    private FirebaseAuth firebaseAuth;

    public GoogleSignInPresenter(GoogleSignInView googleSignInView, FirebaseAuth firebaseAuth) {
        this.googleSignInView = googleSignInView;
        this.firebaseAuth = firebaseAuth;
    }

    public void onSignInClick() {
        googleSignInView.startSignInIntent();
    }


    public void firebaseAuthWithGoogle(GoogleSignInAccount acct) {
        Log.d(TAG, "firebaseAuthWithGooogle:" + acct.getId());
        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        firebaseAuth.signInWithCredential(credential)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d(TAG, "signInWithCredential:onComplete:" + task.isSuccessful());
                        if (!task.isSuccessful()) {
                            Log.w(TAG, "signInWithCredential", task.getException());
                        } else {
                            googleSignInView.startMainActivity();
                        }
                    }
                });
    }
}
