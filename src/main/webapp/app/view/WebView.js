/**
 * View::WebView
 *  - iframe형태로 웹페이지를 출력해 줄 수 있는 뷰 
 */
Ext.define('CALHUB.view.WebView', {
    extend: 'Ext.panel.Panel',
    alias: 'widget.webview',
    border: false,
    html: '<html><body>empty</body></html>',
    initComponent: function() {
        this.callParent(arguments);
    }
});