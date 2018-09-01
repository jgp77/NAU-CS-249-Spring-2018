package p4_Package;

/**
 * Simple class for holding course information for use in a
 * recursive-backtracking process
 *
 * @author MichaelL
 */
public class CourseClass
{

    /**
     * number of students in course
     */
    private int courseSize;

    /**
     * boolean flag to indicate if course is available
     */
    private boolean courseAvailable;

    /**
     * name of course
     */
    private String courseName;

    /**
     * Default constructor
     */
    public CourseClass()
    {
        courseSize = 0;

        courseAvailable = true;

        courseName = "---";
    }

    /**
     * Initialization constructor
     *
     * @param inCourseName course name
     * @param inCourseSize course size
     */
    public CourseClass( String inCourseName, int inCourseSize )
    {
        courseName = inCourseName;

        courseSize = inCourseSize;

        courseAvailable = true;
    }

    /**
     * Copy constructor
     *
     * @param copied CourseClass type value
     */
    public CourseClass( CourseClass copied )
    {
        courseName = copied.courseName;

        courseSize = copied.courseSize;

        courseAvailable = copied.courseAvailable;
    }

    /**
     * sets data for a course
     *
     * @param inCourseName name of course
     * @param inCourseSize size of course
     */
    public void setCourseData( String inCourseName, int inCourseSize )
    {
        courseName = inCourseName;

        courseSize = inCourseSize;

        courseAvailable = true;
    }

    /**
     * used to set or unset the availability of the course
     *
     * @param flagState new state to set course
     */
    public void setCourseAvailable( boolean flagState )
    {
        courseAvailable = flagState;
    }

    /**
     * returns available state of course
     *
     * @return Boolean
     */
    public boolean courseIsAvailable()
    {
        return courseAvailable;
    }

    /**
     * returns number of students in course
     *
     * @return integer number of students in course
     */
    public int getSizeRequest()
    {
        return courseSize;
    }

    /**
     * compares this course to another one
     *
     * @param otherRoom course to be compared to this one
     *
     * @return this greater than other: greater than zero; this equal to other:
     * equals zero this less than other: less than zero
     */
    public int compareTo( CourseClass otherRoom )
    {
        return compareStrings( courseName, otherRoom.courseName );
    }

    /**
     * returns information about course object
     *
     * @return object information as string in the form "Course Name: _course
     * name_, Course Size: _course size_
     */
    public String toString()
    {
        return "Course Name: " + courseName
                + ", Course Size: " + courseSize;
    }

    /**
     * utility compares two strings, utility method for other methods
     *
     * @param oneString   one of the two strings to be compared
     * @param otherString the other of the two strings to be compared
     *
     * @return first greater than second: greater than zero; first equal to
     * second: equals zero first less than second: less than zero
     */
    public int compareStrings( String oneString, String otherString )
    {
        int difference, index = 0;

        while(index < oneString.length() && index < otherString.length())
        {
            difference = toLower( oneString.charAt( index ) )
                    - toLower( otherString.charAt( index ) );

            if(difference != 0)
            {
                return difference;
            }

            index++;
        }

        return oneString.length() - otherString.length();
    }

    /**
     * utility for setting a single upper case letter to lower case
     *
     * @param testChar character to be set to lower case if it is an upper case
     *                 letter
     *
     * @return character lower case of given upper case letter; unchanged input
     * character if input is not upper case letter
     */
    char toLower( char testChar )
    {
        if(testChar >= 'A' && testChar <= 'Z')
        {
            return (char) ( testChar - 'A' + 'a' );
        }

        return testChar;
    }

}
