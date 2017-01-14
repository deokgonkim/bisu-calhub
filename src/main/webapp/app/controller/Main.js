Ext.define('CALHUB.controller.Main', {
    extend: 'Ext.app.Controller',
    init: function() {
        this.control({
            'menutree' : {
                itemclick: this.openPanel
            }
        });
        this.application.on({
            tabopen: this.onTabOpen,
            scope: this
        });
        if ( console != undefined ) console.log('Initialized Main!');
    },
    onTabOpen: function(menuObj) {
        var openedTab = this.getTabPanel().down('#' + menuObj.id);
        if ( openedTab == null ) {
            var newTab = null;
            if ( menuObj.viewName == 'webview') {
                newTab = this.getTabPanel().add({
                    id: menuObj.id,
                    title: menuObj.text,
                    xtype: menuObj.viewName,
                    closable: true,
                    html: '<iframe width="100%" height="100%" src="' + menuObj.url + '"></iframe>'
                });
            } else {
                newTab = this.getTabPanel().add({
                    id: menuObj.id,
                    title: menuObj.text,
                    xtype: menuObj.viewName,
                    closable: true
                });
            }
            this.getTabPanel().setActiveTab(newTab);
            this.application.fireEvent('reload');
        } else {
            this.getTabPanel().setActiveTab(openedTab);
            this.application.fireEvent('reload');
        }
    },
    openPanel: function(obj, record, item, index, e, eOpts) {
        this.application.fireEvent('tabopen', record.data);
    },
    refs: [{
        selector: 'viewport > #tabPanel',
        ref: 'tabPanel'
    }],
    stores: [ 'Users', 'Menus' ],
    models: [ 'User', 'Menu' ],
    views: [
        'MenuTree',
        'WebView'
    ]
});