package cn.itcast.test;

import com.baidu.aip.face.AipFace;
import com.baidu.aip.util.Base64Util;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.nio.file.Files;

/**
 * this file is to test the baidu ai api functions
 */

public class FaceTest {
    private AipFace client;
    @Before
    public void init(){
        client = new AipFace("19441827","GxUxRAfKVEqIgI9vg6muQnlc","LBs3MNM5Os8HzIq2xZTLTQgAYvYWZAy9");

    }
    @Test
    public void testFaceRegister() throws IOException {
        //1 create java code and baidu cloud's interacting client object
        //AipFace client = new AipFace("19441827","GxUxRAfKVEqIgI9vg6muQnlc","LBs3MNM5Os8HzIq2xZTLTQgAYvYWZAy9");
        //2 parameter settings
        HashMap<String,String> options = new HashMap<String, String>();
        options.put("quality_control","NORMAL");//picture quality: NONE,LOW,NORMAL,HIGH   NONE by default
        options.put("liveness_control","LOW");//liveness detection: NONE,LOW,NORMAL,HIGH  NONE by default
        //3 picture construction
        String path  = "F:\\workspace\\testPic\\pic4.jpg";
        // url address, Base64 string
        byte[] bytes = Files.readAllBytes(Paths.get(path));
        String encode = Base64Util.encode(bytes);

        //4 call api and finish face register
       JSONObject res = client.addUser(encode,"BASE64","itcast","1000",options);
       System.out.println(res.toString());
    }
    /**
     * face detection, detect whether face is contained in the picture
     */
    @Test
    public void testFaceCheck() throws IOException {
        // 2. construct pic
        String path  = "F:\\workspace\\testPic\\pic4.jpg";
        byte[] bytes = Files.readAllBytes(Paths.get(path));
        String image = Base64Util.encode(bytes);
        // call api to do face detection
        JSONObject res = client.detect(image,"BASE64",null);
        System.out.println(res.toString(2));
    }
    /**
     * face search
     *
     *
     */
    @Test
    public void testFaceSearch() throws IOException {
        //construct picture
        String path  = "F:\\workspace\\testPic\\pic5.jpg";
        byte[] bytes = Files.readAllBytes(Paths.get(path));
        String image = Base64Util.encode(bytes);
        //face search
        JSONObject res = client.search(image,"BASE64","itcast",null);
        System.out.println(res.toString(2));

    }

    /**
     *  face update
     */

    @Test
    public void testFaceUpdate() throws IOException {
        HashMap<String,String> options = new HashMap<String, String>();
        options.put("quality_control","NORMAL");//picture quality: NONE,LOW,NORMAL,HIGH   NONE by default
        options.put("liveness_control","LOW");//liveness detection: NONE,LOW,NORMAL,HIGH  NONE by default
        //3 picture construction
        String path  = "F:\\workspace\\testPic\\pic4.jpg";
        // url address, Base64 string
        byte[] bytes = Files.readAllBytes(Paths.get(path));
        String encode = Base64Util.encode(bytes);
        JSONObject res = client.updateUser(encode,"BASE64","itcast","1000",options);
    }

}
