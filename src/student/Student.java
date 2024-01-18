package student;

public class Student {
    private String id;
    private String classname;
    private String name;
    private String classnum;
    private String schoolname;
    private String major;
    private String gender;
    public void getAll(){
        System.out.println("添加结果：\t"+ this.name + "\t"+ this.classnum + "\t"+ this.classname+ "\t" + this.major+ "\t" + this.gender + "\t"+ this.schoolname);
    }
    public String getClassName(){
        return this.classname;
    }
    public String getClassnum(){
        return this.classnum;
    }
    public String getName(){
        return this.name;
    }
    public String getSchoolName(){
        return this.schoolname;
    }
    public String getMajor(){
        return this.major;
    }
    public String getGender(){
        return this.gender;
    }
    public String getId(){return this.id;}

    public void setClassnum(String newClassNum){
        this.classnum = newClassNum;
    }
    public void setClassname(String newClassName){
        this.classname = newClassName;
    }
    public void setSchoolname(String newSchoolName){
        this.schoolname = newSchoolName;
    }
    public void setName(String newName){
        this.name = newName;
    }
    public void setMajor(String newMajor){
        this.major = newMajor;
    }
    public void setGender(String gender){
        this.gender= gender;
    }
    public void setId(String newId){
        this.id = newId;
    }
}
