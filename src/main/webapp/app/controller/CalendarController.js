Ext.define('CALHUB.controller.CalendarController', {
    extend: 'Ext.app.Controller',
    init: function() {
        if ( console != undefined )
            console.log('Initialized CalendarController!');
        this.control({
            'viewport calendarview': {
                render: this.renderCalendarView
            },
            'useredit button[action=save]': {
                click: this.updateUser
            }
        });
        this.application.on({
            reload: this.renderCalendarView
        });
        this.callParent(arguments);
    },
    stores: [ 'CalendarEvents' ],
    models: [ 'CalendarEvent' ],
    views: [
        'CalendarEventListView',
        'CalendarView'
    ],
    renderCalendarView: function(panel, obj) {
        console.log('CalendarController.renderCalendarView');
        console.log(arguments);
        var viewId = panel.getId();
        var gridView = panel.down('calendareventlistview');
        if ( viewId.indexOf('userCal_') == 0 ) {
            gridView.store = Ext.getStore('CalendarEvents');
            gridView.store.clearData();
            gridView.store.getProxy().url = 'data/cal/user/' + viewId.replace('userCal_', '')+ '.json';
            gridView.store.load();
            gridView.store.data.length;
        }
    }
});