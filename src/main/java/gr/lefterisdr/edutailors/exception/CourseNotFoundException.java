package gr.lefterisdr.edutailors.exception;

public class CourseNotFoundException extends Exception
{
    public CourseNotFoundException()
    {
        super("CourseNotFound");
    }
}
