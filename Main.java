
import java.io.*;



public class Main {
    public static String mode = null;
    public static int key = 0;
    public static String data = null;
    public static String inputFilePath = null;
    public static String outputFilePath = null;
    public static String output = null;
    public static String algo = null;
    public static String small = "abcdefghijklmnopqrstuvwxyz";
    public static String caps = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";



    public static void enc() throws Exception {
        if (data == null &&  inputFilePath != null) {
            data = readFile(inputFilePath);
        } else if (data == null && inputFilePath == null) {
            data = "";
        }

            char[] inp = new char[data.length()];

        if (algo.equals("unicode")) {
        for (int i = 0; i < data.length(); i++) {
            char p = data.charAt(i);
            for (int k = 0; k < key; k++) {
                p++;
            }

            inp[i] = p;
        } } else {
            for (int i = 0; i < data.length(); i++) {
                Character p = data.charAt(i);
                if (small.contains(p.toString())) {
                for (int k = 0; k < key; k++) {
                    if (p == 'z') {
                        p = 'a';
                        k++;
                    }
                    p++;
                }} else if (caps.contains(p.toString()))        {
                    for (int k = 0; k < key; k++) {
                        if (p == 'Z') {
                            p = 'A';
                            k++;
                        }
                        p++;
                    }
                }

                inp[i] = p;
            }

        }
        StringBuilder out = new StringBuilder(inp.length);
        for (char s : inp) {
            out.append(s);
        }
         output = out.toString();
        if (outputFilePath == null) {
            System.out.println(output);
        } else {
            createFile(outputFilePath);
            writeFile(outputFilePath);
        }



    }
    public static void createFile(String filename) {
        try {
            File myObj = new File(filename);
            if (!myObj.createNewFile()) {
                System.out.println("Error: File already exists.");
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());

        }

    }
    public static void writeFile(String filename) {
        try {
            FileWriter myWriter = new FileWriter(filename);
            myWriter.write(output);
            myWriter.close();

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

    }

    public static String readFile(String fileName) throws Exception {

        try {
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();

            while (line != null) {
                sb.append(line);
                sb.append("\n");
                line = br.readLine();
            }
            br.close();
            return sb.toString();
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }

    }

    public static void dec() throws Exception {
        if (data == null &&  inputFilePath != null) {
            data = readFile(inputFilePath);
        } else if (data == null && inputFilePath == null) {
            data = "";
        }
        char[] inp = new char[data.length()];
        if (algo.equals("unicode")) {
        for (int i = 0; i < data.length(); i++) {
            char p = data.charAt(i);
            for (int k = 0; k < key; k++) {
                p--;
            }

            inp[i] = p;
        } } else {
            for (int i = 0; i < data.length(); i++) {
                Character p = data.charAt(i);
                if (small.contains(p.toString())) {
                    for (int k = 0; k < key; k++) {
                        if (p == 'a') {
                            p = 'z';
                            k++;
                        }
                        p--;
                    }} else if (caps.contains(p.toString()))        {
                    for (int k = 0; k < key; k++) {
                        if (p == 'A') {
                            p = 'Z';
                            k++;
                        }
                        p--;
                    }
                }

                inp[i] = p;
            }

        }
        StringBuilder out = new StringBuilder(inp.length);
        for (char s : inp) {
            out.append(s);
        }
         output = out.toString();
        if (outputFilePath == null) {
            System.out.println(output);
        }else {
            createFile(outputFilePath);
            writeFile(outputFilePath);
        }
    }
    public static void main(String[] args) throws  Exception{
        int i = 0;
        while (i < args.length) {
            switch (args[i]) {
                case "-mode":
                     mode = args[i + 1];
                    break;
                case "-key":
                     try{
                         key = Integer.parseInt(args[i + 1]);
                     } catch (Exception e) {
                         System.out.println("Error:  " + e.getMessage());
                     }
                    break;
                case "-data":
                     data = args[i + 1];
                    break;
                case "-in":
                    inputFilePath = args[i + 1];
                    break;
                case "-out":
                    outputFilePath = args[i + 1];
                    break;
                case "-alg" :
                    algo = args[i + 1];
                    break;

            }
            i += 2;
        }
        if (mode.equals("dec")) {
            dec();
        } else if(mode.equals("enc") || mode == null) {
            enc();
        } else {
            System.out.println("Error: unknown mode");
        }


    }
}

