package top.jisy.docs.constant;


// @todo unify response parameters
public abstract class ResponseParameters {

    public static final String HTTP_STATUS = "status";
    public static final String STATUS_CODE = "code";
    public static final String STATUS_DESCRIPTION = "description";
    public static final String MESSAGE = "message";
    public static final String DOCUMENT = "document";
    public static final String DOCUMENT_LIST = "documents";
    public static final String DOCUMENT_ICON = "icon";
    public static final String COLLABORATOR_LIST = "collaborators";
    public static final String USER = "user";
    public static final String LOGGED_IN = "Logged in successfully.";
    public static final String ALREADY_LOGGED_OUT = "You are already logged out";
    public static final String LOGGED_OUT = "Successfully logged out.";
    public static final String INVALID_PASSWORD = "Invalid username or password";
    // doc
    public static final String CREATE_DOCUMENT_NOT_LOGGED_IN = "You have to login to be able to create a new document.";
    public static final String DOCUMENT_CREATED = "Document was created successfully.";
    public static final String DOCUMENT_LOADED = "Documents loaded successfully";
    public static final String ADD_COLLABORATOR_NOT_LOGGED_IN = "You have to login to be able to add a collaborator.";


    // collaborator
    // @todo
    public static final String COLLABORATOR_ADDED = "The collaborator '%s' was successfully added to your document.";
    public static final String REMOVE_COLLABORATOR_NOT_LOGGED_IN = "You have to login to be able to remove a collaborator.";
    public static final String COLLABORATOR_REMOVED = "The collaborator '%s' was successfully removed from your document.";
    private ResponseParameters() {
    }

    //public static final String
}
