package com.blackchopper.briefness;

import com.blackchopper.briefness.databinding.JavaLayout;
import com.blackchopper.briefness.util.ClassUtil;
import com.blackchopper.briefness.util.FileUtil;

import java.io.File;

import static com.blackchopper.briefness.XmlProxyInfo.SPLIT;
import static com.blackchopper.briefness.XmlProxyInfo.readTextFile;

/**
 * author  : Black Chopper
 * e-mail  : 4884280@qq.com
 * github  : http://github.com/BlackChopper
 * project : Briefness
 */

public class JavaInjector {
    public static final String PACKAGE_NAME = ClassUtil.findPackageName();
    public static final String PACKAGE = "package " + PACKAGE_NAME + ".briefness;\n";
    public static final String IMPORT = "import android.graphics.Bitmap;\n" +
            "import android.util.Log;\n" +
            "import android.view.View;\n" +
            "import android.widget.Button;\n" +
            "import android.widget.EditText;\n" +
            "import android.widget.ImageView;\n" +
            "import android.widget.TextView;\n" +
            "import " + PACKAGE_NAME + ".briefness.BriefnessInjector;\n";

    public static final String CLASS_UP = "public class ViewInjector {\n" +
            "    public static void inject(View view, Object value) {\n" +
            "        if (value == null | view == null) {\n" +
            "        } else if (";
    public static final String CLASS_DOWN = ") {\n" +
            "        } else if (view instanceof ImageView) {\n" +
            "            injectImageView((ImageView) view, value);\n" +
            "        } else if (view instanceof Button) {\n" +
            "            injectButton((Button) view, value);\n" +
            "        } else if (view instanceof EditText) {\n" +
            "            injectEditText((EditText) view, value);\n" +
            "        } else if (view instanceof TextView) {\n" +
            "            injectTextView((TextView) view, value);\n" +
            "        } else {\n" +
            "            Log.e(\"Briefness\", \"No match method and can not inject \" + view.getClass().getSimpleName());\n" +
            "        }\n" +
            "    }\n" +
            "\n" +
            "    private static void injectEditText(EditText view, Object value) {\n" +
            "        if (value instanceof String) {\n" +
            "            view.setText((String) value);\n" +
            "        }  \n" +
            "    }\n" +
            "\n" +
            "\n" +
            "    private static void injectButton(Button view, Object value) {\n" +
            "        if (value instanceof String) {\n" +
            "            view.setText((String) value);\n" +
            "        } \n" +
            "    }\n" +
            "\n" +
            "    private static void injectImageView(ImageView view, Object value) {\n" +
            "        if (value instanceof Bitmap) {\n" +
            "            view.setImageBitmap((Bitmap) value);\n" +
            "        } \n" +
            "    }\n" +
            "\n" +
            "    private static void injectTextView(TextView view, Object value) {\n" +
            "        if (value instanceof String) {\n" +
            "            view.setText((String) value);\n" +
            "        }  \n" +
            "    }\n" +
            "\n" +
            "  \n" +
            "}\n";
    boolean debug = false;

    public void witeCode() {
        if (fileExits()) return;
        StringBuilder builder = new StringBuilder();
        builder.append(PACKAGE)
                .append(IMPORT)
                .append(JavaLayout.author)
                .append(CLASS_UP)
                .append("BriefnessInjector.onInject(view,value)")
                .append(CLASS_DOWN);
        String java;
        String module = readTextFile(System.getProperty("user.dir") + "/BriefnessConfig");
        if (debug) {
            java = System.getProperty("user.dir") + SPLIT + module.replace(" ", "").replace("/", "") + SPLIT
                    + "build/generated/source/apt/debug/"
                    + PACKAGE_NAME.replace(".", "/")
                    + "/briefness/ViewInjector.java";
        } else {
            java = System.getProperty("user.dir") + SPLIT + module.replace(" ", "").replace("/", "") + SPLIT
                    + "build/generated/source/apt/release/"
                    + PACKAGE_NAME.replace(".", "/")
                    + "/briefness/ViewInjector.java";
        }
        FileUtil.createFile(java, builder.toString());
    }

    public boolean fileExits() {

        String module = readTextFile(System.getProperty("user.dir") + "/BriefnessConfig");
        String java = System.getProperty("user.dir") + SPLIT + module.replace(" ", "").replace("/", "") + SPLIT
                + "build/generated/source/apt/debug/";
        if (new File(java).exists()) {

            debug = true;
        } else {
            debug = false;
        }
        java = System.getProperty("user.dir") + SPLIT + module.replace(" ", "").replace("/", "") + SPLIT
                + "build/generated/source/apt/debug/"
                + PACKAGE_NAME.replace(".", "/")
                + "/briefness/ViewInjector.java";
        boolean flag = false;
        if (new File(java).exists()) {

            flag = true;
        } else {

            flag = false;
        }

        java = System.getProperty("user.dir") + SPLIT + module.replace(" ", "").replace("/", "") + SPLIT
                + "build/generated/source/apt/release/"
                + PACKAGE_NAME.replace(".", "/")
                + "/briefness/ViewInjector.java";

        if (new File(java).exists()) {

            flag = true | flag;
        } else {

            flag = false | flag;
        }

        return flag;
    }
}
