package gr.lefterisdr.edutailors.exception;

public class StudentNotFoundException extends Exception
{
    public StudentNotFoundException()
    {
        super("StudentNotFound");
    }
}
