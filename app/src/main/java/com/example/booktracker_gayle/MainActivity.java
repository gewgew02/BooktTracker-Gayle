package com.example.booktracker_gayle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;


public class MainActivity extends AppCompatActivity {
    Button borrowButton, clearButton;
    EditText enterBookCode, enterNumDays, displayAuthor, displayTitle, displayPrice;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    String TAG = "Firebase";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = FirebaseFirestore.getInstance();

        enterBookCode = findViewById(R.id.enterBookCode);
        enterNumDays = findViewById(R.id.enterNumDays);
        borrowButton = findViewById(R.id.borrowButton);
        displayAuthor = findViewById(R.id.displayAuthor);
        displayTitle = findViewById(R.id.displayTitle);
        displayPrice = findViewById(R.id.displayPrice);
        clearButton = findViewById(R.id.clearButton);

        borrowButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String bookCode = enterBookCode.getText().toString();
                String borrowDays = enterNumDays.getText().toString();
            db.collection("Books")
                    .document(bookCode)
                    .get()
                    .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                        @Override
                        public void onSuccess(DocumentSnapshot documentSnapshot) {
                            if (documentSnapshot.exists()) {
                                // Document exists, book code found
                                if (!documentSnapshot.getBoolean("isBorrowed")) {
                                    displayAuthor.setText(documentSnapshot.getString("author"));
                                    displayTitle.setText(documentSnapshot.getString("title"));
                                    if (documentSnapshot.getString("type").equalsIgnoreCase("PremiumBooks")) {
                                       PremiumBook prembook = new PremiumBook(Integer.parseInt(borrowDays));
                                        displayPrice.setText(Double.toString(prembook.calculateTotalPrice()));
                                    }
                                    if (documentSnapshot.getString("type").equalsIgnoreCase("RegularBooks")) {
                                        RegularBook regbook = new RegularBook(Integer.parseInt(borrowDays));
                                        displayPrice.setText(Double.toString(regbook.calculateTotalPrice()));
                                    }
                                } else {
                                    Toast.makeText(MainActivity.this, "The Book is not Available!!", Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                // Document doesn't exist, book code not found
                                displayAuthor.setText(" ");
                                displayTitle.setText(" ");
                                displayPrice.setText(" ");
                                enterNumDays.setText(" ");
                                Toast.makeText(MainActivity.this, "Book Code not Found!!", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            // Error occurred while fetching the document
                            Toast.makeText(MainActivity.this, "Error fetching document: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
            }
        });

        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayAuthor.setText(" ");
                displayTitle.setText(" ");
                displayPrice.setText(" ");
                enterNumDays.setText(" ");
                enterBookCode.setText(" ");
            }
        });
    }
}