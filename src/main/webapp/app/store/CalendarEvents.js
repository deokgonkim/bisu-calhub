Ext.define('CALHUB.store.CalendarEvents', {
    extend: 'Ext.data.Store',
    model: 'CALHUB.model.CalendarEvent',
    autoLoad: false,
    proxy: {
        type: 'rest',
        url: 'data/cal/user/',
        reader: {
            type: 'json',
            root: 'eventList'
        }
    },
    root: {
        id: 'eventList'
    }
});