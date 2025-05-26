package src.proj;

public class User {
    private String name;
    private String bio;
    private int age;

    public User(String name, String bio, int age)
    {
        this.name = name;
        this.bio = bio;
        this.age = age;
    }

    public String getName() {return name;}
    public String getBio() {return bio;}
    public int getAge() {return age;}

    @Override
    public String toString()
    {
        return name;
    }

    @Override
    public boolean equals(Object obj)
    {
        return obj instanceof User && ((User)obj).name.equals(this.name);
    }

    @Override
    public int hashCode()
    {
        return name.hashCode();
    } 
}
