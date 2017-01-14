describe("CalendarEvents", function() {
    var store = null, ctlr = null;

    beforeEach(function(){
        if (!ctlr) {
            ctlr = Application.getController('CalendarController');
        }
        
        if (!store) {
            store = ctlr.getStore('CalendarEvents');
        }
        
        expect(store).toBeTruthy();
        store.getProxy().url = 'data/cal/user/dgkim.json';
        store.load();
        
        waitsFor(
            function(){ return !store.isLoading(); },
            "load never completed",
            4000
        );
    });
    
    it("should have events",function(){
        expect(store.getCount()).toBeGreaterThan(0);
    });
});