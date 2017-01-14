Ext.define('CALHUB.view.CalendarEventListView', {
    extend: 'Ext.grid.Panel',
    alias: 'widget.calendareventlistview',
    border: false,
    store: 'CalendarEvents',
    selType: 'rowmodel',
    initComponent: function() {
        console.log('Initialized CalendarEventListView!');
        this.columns = [
            { header: '요약', dataIndex: 'summary', flex: 1, editor: 'textfield' },
            { header: '소유자', dataIndex: 'organizer', flex: 1, editor: 'textfield' },
            { header: '위치', dataIndex: 'location', flex: 1, editor: 'textfield' },
            { header: '시작', dataIndex: 'startDate', renderer: function(startDate) { return Ext.Date.format(new Date(startDate), 'Y-m-d H:i'); }, flex: 1, editor: 'textfield' },
            { header: '종료', dataIndex: 'endDate', renderer: function(endDate) { return Ext.Date.format(new Date(endDate), 'Y-m-d H:i'); }, flex: 1, editor: 'textfield' }
        ];
        this.callParent(arguments);
    },
    /*plugins: [
        Ext.create('Ext.grid.plugin.RowEditing', {
            autoCancel: true,
            clicksToEdit: 1
        })
    ]*/
});