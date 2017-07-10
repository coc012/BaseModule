package com.coc.basemodule.component.camera;

public class CameraKitResultHolder {


    private static byte[] jpegArray;

    public static byte[] getJpegArray() {
        return jpegArray;
    }

    public static void setJpegArray(byte[] Array) {
        CameraKitResultHolder.jpegArray = Array;
    }


    public static void dispose() {
        setJpegArray(null);
    }

}
