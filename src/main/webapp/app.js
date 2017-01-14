//Ext.Loader.setConfig({enabled:true});
Ext.application({
    name: 'CALHUB',
    appFolder: 'app',
    autoCreateViewport: true,
    launch: function() {
        
    },
    controllers: [
        'Main',
        'CalendarController'
    ]
});