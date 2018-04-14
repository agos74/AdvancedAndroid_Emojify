package com.example.android.emojify;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.util.SparseArray;
import android.widget.Toast;

import com.google.android.gms.vision.Frame;
import com.google.android.gms.vision.face.Face;
import com.google.android.gms.vision.face.FaceDetector;

public class Emojifier {

    static final String TAG = Emojifier.class.toString();

    static int detectFaces(Context context, Bitmap picture) {

        // Create the face detector, disable tracking and enable classifications
        FaceDetector detector = new FaceDetector.Builder(context)
                .setTrackingEnabled(false)
                .setClassificationType(FaceDetector.ALL_CLASSIFICATIONS)
                .build();

        // Check FaceDetector operational status
        if (!detector.isOperational()) {
            // Face Detector is not operational...
            Toast.makeText(context, "Face Detector is not in operational status... detecting faces is unavailable!", Toast.LENGTH_SHORT).show();
        }

        // Create Frame instance from the bitmap to supply to the detector
        Frame frame = new Frame.Builder().setBitmap(picture).build();

        // The detector can be called synchronously with a frame to detect faces
        SparseArray<Face> faces = detector.detect(frame);

        // Releasing the face detector, that use native resources
        detector.release();

        // count faces detected
        int result = faces.size();

        // Log the number of faces detected
        Log.d(TAG, "Faces detected: " + result);

        // No faces detected
        if (result == 0) {
            Toast.makeText(context, "No Faces Detected", Toast.LENGTH_SHORT).show();
        }

        return result;
    }
}
