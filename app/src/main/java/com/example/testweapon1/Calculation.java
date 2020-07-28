package com.example.testweapon1;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;
import androidx.drawerlayout.widget.DrawerLayout;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.provider.MediaStore;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.github.chrisbanes.photoview.OnPhotoTapListener;
import com.github.chrisbanes.photoview.OnViewTapListener;
import com.github.chrisbanes.photoview.PhotoView;
import com.google.android.material.navigation.NavigationView;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Calculation extends AppCompatActivity {
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;
    //private TextView textView;
    private NavigationView navigationView;
    ArrayList<Integer> coordinates;
    ArrayList<Double> calculation;
    private EditText unit;
    private Button done;
    int x = 0;

    private static final int CAMERA_REQUEST = 1888;
    private static final int MY_CAMERA_PERMISSION_CODE = 100;
    PhotoView photoView;

    private String currentPhotoPath;

    private static final int PERMISSION_CODE = 1001;
    private final int IMG_REQUEST = 1;
    Bitmap bitmap;
    private TextView date;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculation);
        photoView = findViewById(R.id.photo_view);
        unit = findViewById(R.id.unit);
        done = findViewById(R.id.done);
        photoView.setZoomable(false);
        date = findViewById(R.id.date);

        final ImageView iv = new ImageView(Calculation.this);
        coordinates = new ArrayList();
        calculation = new ArrayList();
        final RelativeLayout rl = (RelativeLayout) findViewById(R.id.relativeLayout);
        unit.setVisibility(View.GONE);
        done.setVisibility(View.GONE);

        final Handler h = new Handler();
        h.postDelayed(new Runnable()
        {
            private long time = 0;

            @Override
            public void run()
            {
                // do stuff then
                // can call h again after work!
                Date currentTime = Calendar.getInstance().getTime();
                h.postDelayed(this, 1000);
                if(currentTime.getHours() > 0 && currentTime.getHours() < 11 ){
                    date.setText(currentTime.toLocaleString() + "\n" + "Good Morning");
                }else if(currentTime.getHours() >= 11 && currentTime.getHours() <= 15 ){
                    date.setText(currentTime.toLocaleString() + "\n" + "Good Afternoon");
                }else {
                    date.setText(currentTime.toLocaleString() + "\n" + "Good Evening");
                }

            }
        }, 1000); // 1 second delay (takes millis)


        photoView.setOnViewTapListener(new OnViewTapListener() {

            @Override
            public void onViewTap(View view, float x, float y) {

                if (unit.getText().toString().trim().equals("")) {
                    Toast.makeText(Calculation.this, "Calibration Value is Empty", Toast.LENGTH_SHORT).show();
                    calculation.clear();
                    coordinates.clear();
                } else {
                    iv.setVisibility(View.VISIBLE);
                    photoView.setOnPhotoTapListener(new OnPhotoTapListener() {
                        @Override
                        public void onPhotoTap(ImageView view, float x, float y) {
                            if (calculation.size() == 8) {
                                Toast.makeText(Calculation.this, "Sorry", Toast.LENGTH_SHORT).show();
                            } else {
                                calculation.add((double) x);
                                calculation.add((double) y);
                            }
                        }
                    });
                    if (coordinates.size() == 8) {
                        //Toast.makeText(Calculation.this, "Sorry", Toast.LENGTH_SHORT).show();
                    } else {
                        Bitmap bm = BitmapFactory.decodeResource(getResources(),
                                R.drawable.name);
                        iv.setImageBitmap(bm.createScaledBitmap(bm, 135, 135, true));
                        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                                RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);

                        params.leftMargin = (int) (x - 65);
                        params.topMargin = (int) (y - 65); // + 45

//                        coordinates.add((int)((x)-20));
//                        coordinates.add((int) (y));

                        coordinates.add((int) (x - 65));
                        coordinates.add((int) (y - 35));


                        switch (coordinates.size()) {
                            case 2:
                                Toast.makeText(Calculation.this, "1st point selected", Toast.LENGTH_SHORT).show();
                                break;
                            case 4:
                                Toast.makeText(Calculation.this, "2nd point selected", Toast.LENGTH_SHORT).show();
                                break;
                            case 6:
                                System.out.println(coordinates.get(5));
                                Toast.makeText(Calculation.this, "3rd point selected", Toast.LENGTH_SHORT).show();
                                break;
                            case 8:
                                Toast.makeText(Calculation.this, "4th point selected", Toast.LENGTH_SHORT).show();
                                break;

                        }


                        if (coordinates.size() == 4) {
//                        LineView  mLineView = findViewById(R.id.lineView);
//                        mLineView.setVisibility(View.VISIBLE);
//                        mLineView.setPointA(new PointF(coordinates.get(0), coordinates.get(1)));
//                        mLineView.setPointB(new PointF(coordinates.get(2), coordinates.get(3)));
//                        mLineView.draw();

//                            DrawView drawView = new DrawView(MainActivity.this);
//                            RelativeLayout relativeLayout = findViewById(R.id.relativeLaoyout1);
//                            relativeLayout.addView(drawView);
//                            photoView.setImageBitmap(bitmap);
//                            System.out.println("hello");

//                            Paint mPaint;
//                            final Bitmap bitmap = ((BitmapDrawable)photoView.getDrawable()).getBitmap().copy(Bitmap.Config.ARGB_8888, true);
//                            Canvas canvas = new Canvas(bitmap);
//                            mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
//                            mPaint.setAntiAlias(true);
//                            mPaint.setDither(true);
//                            mPaint.setColor(Color.BLUE);
//                            mPaint.setStyle(Paint.Style.STROKE);
//                            mPaint.setStrokeJoin(Paint.Join.ROUND);
//                            mPaint.setStrokeCap(Paint.Cap.ROUND);
//                            mPaint.setStrokeWidth(5);


//                            PointF pointA = new PointF(coordinates.get(0), coordinates.get(1));
//                            PointF pointB = new PointF(coordinates.get(1), coordinates.get(2));
//                            canvas.drawLine(pointA.x, pointA.y, pointB.x, pointA.y, mPaint);
                            //canvas.drawLine(coordinates.get(0), coordinates.get(1), coordinates.get(2), coordinates.get(3), mPaint);
                            // canvas.drawLine(coordinates.get(0), coordinates.get(1) / 2.45f, coordinates.get(2), coordinates.get(3) / 2.45f, mPaint);
//                            photoView.setImageBitmap(bitmap);
//                            photoView.invalidate();
//                            photoView.requestLayout();
                        } else if (coordinates.size() == 8) {
//                            final LineView  mLineView = findViewById(R.id.lineView);
//                        mLineView.setVisibility(View.VISIBLE);
//                        mLineView.setPointC(new PointF(coordinates.get(4), coordinates.get(5)));
//                        mLineView.setPointD(new PointF(coordinates.get(6), coordinates.get(7)));
//                        mLineView.draw();


                            AlertDialog alertDialog = new AlertDialog.Builder(Calculation.this).create();
                            alertDialog.setTitle("Alert");
                            alertDialog.setMessage("Points saved successfully,Do you want to calculate the Distance and Angle");
                            alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Yes",
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int which) {
                                            calculate();
                                            iv.setVisibility(View.GONE);
                                            //mLineView.setVisibility(View.GONE);
                                        }
                                    });
                            alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "No",
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int which) {

                                            unit.setText("");
                                            calculation.clear();
                                            coordinates.clear();
                                            iv.setVisibility(View.GONE);
                                            Toast.makeText(Calculation.this, "Coordinated deleted successfully", Toast.LENGTH_SHORT).show();
                                        }
                                    });
                            alertDialog.show();

                        }

                        if (iv.getParent() != null) {
                            ((ViewGroup) iv.getParent()).removeView(iv); // <- fix
                        }

                        rl.addView(iv, params);

                    }

                }
            }

        });

        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (x == 0) {
                    if (unit.getText().toString().trim().equals("")) {
                        Toast.makeText(Calculation.this, "Calibration value is Empty", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(Calculation.this, "Unit Saved Successfully", Toast.LENGTH_SHORT).show();
                        unit.setVisibility(View.VISIBLE);
                        done.setVisibility(View.GONE);
                        unit.setText("");
                        calculation.clear();
                        coordinates.clear();
                    }
                } else if (x == 1) {
                    Toast.makeText(Calculation.this, "Thank You!", Toast.LENGTH_SHORT).show();
                    unit.setVisibility(View.GONE);
                    done.setVisibility(View.GONE);
                    photoView.setImageResource(R.drawable.photo);
                    unit.setText("");
                    calculation.clear();
                    coordinates.clear();
                    photoView.setZoomable(false);

                }
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.navigation_menu1, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.capture:
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
                        String[] permissions = {Manifest.permission.READ_EXTERNAL_STORAGE};
                        requestPermissions(permissions, PERMISSION_CODE);
                    } else {
                        String fileName = "dgh1";
                        File storageDirectory = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
                        try {
                            File imageFile = File.createTempFile(fileName, ".jpg", storageDirectory);
                            currentPhotoPath = imageFile.getAbsolutePath();
                            Uri imageUri = FileProvider.getUriForFile(Calculation.this, "com.example.testweapon1.fileprovider", imageFile);
                            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                            intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
                            startActivityForResult(intent, 2);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                } else {
                    String fileName = "dgh";
                    File storageDirectory = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
                    try {
                        File imageFile = File.createTempFile(fileName, ".jpg", storageDirectory);
                        currentPhotoPath = imageFile.getAbsolutePath();
                        Uri imageUri = FileProvider.getUriForFile(Calculation.this, "com.example.testweapon1.fileprovider", imageFile);
                        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
                        startActivityForResult(intent, 2);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                return true;
            case R.id.pick:
                selectImage();
                return true;
            case R.id.exit:
                startActivity(new Intent(Calculation.this, MainActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }


    }

    public void selectImage() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, IMG_REQUEST);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        switch (requestCode) {
            case PERMISSION_CODE: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    String fileName = "dgh";
                    File storageDirectory = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
                    try {
                        File imageFile = File.createTempFile(fileName, ".jpg", storageDirectory);
                        currentPhotoPath = imageFile.getAbsolutePath();
                        Uri imageUri = FileProvider.getUriForFile(Calculation.this, "com.example.testweapon1.fileprovider", imageFile);
                        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
                        startActivityForResult(intent, 2);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    Toast.makeText(this, "Permission Denied....", Toast.LENGTH_SHORT).show();
                }
            }

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if (requestCode == 2 && resultCode == RESULT_OK) {
            bitmap = BitmapFactory.decodeFile(currentPhotoPath);
            PhotoView photoView = findViewById(R.id.photo_view);
            photoView.setImageBitmap(bitmap);
            unit.setVisibility(View.VISIBLE);
            //done.setVisibility(View.VISIBLE);
            unit.setText("");
            photoView.setZoomable(true);


        } else if (requestCode == 1 && resultCode == RESULT_OK) {
            try {
                bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), data.getData());
                photoView.setImageBitmap(bitmap);
                unit.setVisibility(View.VISIBLE);
                //done.setVisibility(View.VISIBLE);
                unit.setText("");
                photoView.setZoomable(true);


                // setContentView(drawView);

            } catch (IOException e) {
                e.printStackTrace();
            }

            //photoView.setImageURI(data.getData());
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    public void calculate() {

        try {
            if (unit.getText().toString().trim().equals("")) {
                Toast.makeText(this, "Calibration value is empty", Toast.LENGTH_SHORT).show();
                calculation.clear();
                coordinates.clear();
            } else if (calculation.size() < 8) {
                Toast.makeText(this, "Missing Coordinates", Toast.LENGTH_SHORT).show();
            } else if (Double.parseDouble(unit.getText().toString()) < 1) {
                Toast.makeText(this, "Wrong Calibration input", Toast.LENGTH_SHORT).show();
            } else {

                double a = calculation.get(0);
                double b = calculation.get(1);
                double c = calculation.get(2);
                double d = calculation.get(3);
                double e = calculation.get(4);
                double f = calculation.get(5);
                double g = calculation.get(6);
                double h = calculation.get(7);

                double answer1 = Math.sqrt(Math.pow((a - c), 2) + Math.pow((b - d), 2));
                double answer2 = Math.sqrt(Math.pow((e - g), 2) + Math.pow((f - h), 2));
                double answer3 = Double.parseDouble(unit.getText().toString());
                double fullImpactLength = ((answer3 / answer1) * answer2);
                calculation.clear();
                unit.setVisibility(View.VISIBLE);

                double p1 = -5.995;
                double p2 = 829.5;
                double p3 = -5554;
                double q1 = -1.69;
                double q2 = -40.59;
//                reset.setVisibility(View.GONE);
//                calc.setVisibility(View.GONE);
                double Incidence_Angle = (-5.995 * Math.pow(fullImpactLength, 2) + 829.5 * fullImpactLength - 5554) / (Math.pow(fullImpactLength, 2) - 1.69 * fullImpactLength - 40.59);
                //double answer5 = (p1 * Math.pow(answer4, 2) + p2 * answer4 + p3) / (Math.pow(answer4, 2) - q1 * answer4 + q2);
                unit.setText(" Length: " + String.format("%.2f", fullImpactLength) + " mm" + "\n" + "Angle: " + String.format("%.2f", Incidence_Angle) + "°");

                done.setVisibility(View.VISIBLE);
                x = 1;
            }

        } catch (Exception e) {
            Toast.makeText(this, "Wrong Input", Toast.LENGTH_SHORT).show();
        }

    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode==KeyEvent.KEYCODE_BACK)
            System.out.println("Back Pressed");
        return false;
        // Disable back button..............
    }
}