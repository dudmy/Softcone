package softcone.csapp.list;

/**
 * Created by YuJin on 2015-04-01.
 */
public class NoticeData implements OnSizeChangedListener {

    private boolean toggle;
    private String notice_title;
    private String notice_info;
    private String date;

    private boolean mIsExpanded;
    private int mCollapsedHeight;
    private int mExpandedHeight;

    public NoticeData(boolean toggle, String notice_title, String notice_info, String date, int collapsedHeight){
        this.toggle = toggle;
        this.notice_title = notice_title;
        this.notice_info = notice_info;
        this.date = date;

        mCollapsedHeight = collapsedHeight;
        mIsExpanded = false;
        mExpandedHeight = -1;
    }

    public boolean getToggle() {
        return toggle;
    }

    public String getNotie_title() {
        return notice_title;
    }

    public String getNotice_info() {
        return notice_info;
    }

    public String getDate() {
        return date;
    }

    public boolean isExpanded() {
        return mIsExpanded;
    }

    public void setExpanded(boolean isExpanded) {
        mIsExpanded = isExpanded;
    }

    public int getCollapsedHeight() {
        return mCollapsedHeight;
    }

    public void setCollapsedHeight(int collapsedHeight) {
        mCollapsedHeight = collapsedHeight;
    }

    public int getExpandedHeight() {
        return mExpandedHeight;
    }

    public void setExpandedHeight(int expandedHeight) {
        mExpandedHeight = expandedHeight;
    }

    @Override
    public void onSizeChanged(int newHeight) {
        setExpandedHeight(newHeight);
    }
}
