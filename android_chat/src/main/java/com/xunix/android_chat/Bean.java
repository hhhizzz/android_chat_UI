package com.xunix.android_chat;

import android.net.Uri;

import java.io.File;
import java.util.Date;

public class Bean {

	/** message   */
	private String tMessage;
	/** avatar    */
	private Integer portrait;
    private Uri portraitURI;
	/**  time when send the message */
	private Date time;
	/** you or me  */
	private int id;

    /**image in the message*/
    private String imagePath;
    private Uri imageUri;

    /**files int the message*/
    private File file;




	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String gettMessage() {
		return tMessage;
	}
    public String getImagePath(){
        return imagePath;
    }
    public Uri getPortraitURI(){return portraitURI;}
    public Uri getImageUri(){
        return imageUri;
    }
    public File getFiles(){
        return file;
    }
    public Date getTime() {
        return time;
    }
    public Integer getPortrait() {
        return portrait;
    }



	public void settMessage(String tMessage) {
		this.tMessage = tMessage;
	}

	public void setPortrait(Integer portrait) {
		this.portrait = portrait;
	}

	public void setTime(Date time) {
		this.time = time;
	}
    public void setPortraitURI(Uri portraitURI){this.portraitURI=portraitURI;}
    public void setFilesPath(File filesPath){
        this.file=filesPath;
    }


	public Bean(String tMessage, Integer portrait, Date time,
				int id) {
		super();
		this.tMessage = tMessage;
		this.portrait = portrait;
		this.time = time;
		this.id = id;
	}
    public Bean(String tMessage, Integer portrait, Date time
            ,String imagePath,int id) {
        super();
        this.tMessage = tMessage;
        this.portrait = portrait;
        this.time = time;
        this.id = id;
        this.imagePath=imagePath;
    }
    public Bean(String tMessage, Integer portrait, Date time,
                Uri ImageUri,int id) {
        super();
        this.tMessage = tMessage;
        this.portrait = portrait;
        this.time = time;
        this.id = id;
        this.imageUri=ImageUri;
    }

	public Bean(String tMessage,Uri portraitURI,Date time,Uri ImageUri,int id) {
        this.tMessage = tMessage;
        this.portraitURI = portraitURI;
        this.time = time;
        this.id = id;
        this.imageUri=ImageUri;
	}
    public Bean(String tMessage,int id) {
        this.tMessage = tMessage;
        this.time = new Date();
        this.id = id;
        if(id==1){
            this.portrait=R.drawable.me;
        }else{
            this.portrait=R.drawable.you;
        }
    }
    public Bean(Uri imageUri,int id) {
        this.imageUri=imageUri;
        this.time = new Date();
        this.id = id;
        if(id==1){
            this.portrait=R.drawable.me;
        }else{
            this.portrait=R.drawable.you;
        }
    }
    public Bean(File file,int id){
        this.file=file;
        this.time=new Date();
        this.id=id;
        if(id==1){
            this.portrait=R.drawable.me;
        }else{
            this.portrait=R.drawable.you;
        }
    }
	@Override
	public String toString() {
		return "PeopleStudentBean [tMessage=" + tMessage + ", portrait="
				+ portrait + ", time=" + time + ", id=" + id + "]";
	}

}
