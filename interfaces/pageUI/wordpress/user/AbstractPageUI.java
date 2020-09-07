package pageUI.wordpress.user;

public class AbstractPageUI {
	public static final String PAGES_LINK = "//a[contains(@class,'menu-icon-page')]";
	public static final String MEDIA_LINK = "//a[contains(@class,'menu-icon-media')]";
	public static final String POSTS_LINK = "//a[contains(@class,'menu-icon-post')]";
	
	public static final String DYNAMIC_PAGE_LINK = "//div[@class= 'wp-menu-name' and text()='%s']";
	public static final String UPLOAD_FILE_TYPE = "//input[@type='file']";
	public static final String MEDIA_PROGRESS_BAR = "//div[@class='thumbnail']/div[@class='media-progress-bar']";
	public static final String MEDIA_ALL_UPLOADED_VALUE = "//div[@class='thumbnail']//img";
	
	public static final String DYNAMIC_SUCCESS_MESSAGE_ON_POSTS_OR_PAGE_PAGE = "//div[@id='message']/p[contains(text(),'%s')]";
	public static final String DYNAMIC_ROW_VALUE_AT_COLUMN_NAME = "//td[@data-colname='%s']//a[text()='%s']";
	public static final String DYNAMIC_POST_WITH_CATEGORY_TITLE_DATE = "//p[@class='post-categories']/a[text()='%s']/parent::p/following-sibling::h2[@class='post-title']/a[text()='%s']/parent::h2/following-sibling::p[@class='post-meta']/a[text()='%s']";
	public static final String DYNAMIC_POST_IMAGE_WITH_TITLE = "//a[@title='%s']/img[contains(@src, '%s')]";
	public static final String DYNAMIC_POST_TITLE = "//header[@class='post-header']//a[text()='%s']";
	
	public static final String SEARCH_ICON = "//a[@class='search-toggle']";
	public static final String SEARCH_TEXTBOX = "//input[@class='search-field']";
	public static final String SEARCH_BUTTON = "//span[@class='fa fw fa-search']";
	
	
	

}
