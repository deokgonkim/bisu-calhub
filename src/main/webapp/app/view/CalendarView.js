Ext.define('CALHUB.view.CalendarView', {
    extend: 'Ext.panel.Panel',
    alias: 'widget.calendarview',
    border: false,
    initComponent: function() {
        console.log('Initialized CalendarView!');
        this.callParent(arguments);
    },
    items: [{
        xtype: 'panel',
        title: 'aaa'
    },{
        xtype: 'calendareventlistview',
    }]
});