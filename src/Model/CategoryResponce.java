package Model;

public class CategoryResponce {

    public enum CategoryResult {C_NOT_EXISTS, C_DELETED, C_EXISTS, C_CREATED, C_MODIFIED}

    private String message;
    private Category wholesaler;
    private CategoryResult result;


    public CategoryResponce() {
        this.message = "";
        this.wholesaler = new Category();
        this.result = null;
    }

    public CategoryResponce(String message, Category wholesaler, CategoryResult result) {
        this.message = message;
        this.wholesaler = wholesaler;
        this.result = result;
    }


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Category getCategory() {
        return wholesaler;
    }

    public void setCategory(Category wholesaler) {
        this.wholesaler = wholesaler;
    }

    public CategoryResult getResult() {
        return result;
    }

    public void setResult(CategoryResult result) {
        this.result = result;
    }
}
