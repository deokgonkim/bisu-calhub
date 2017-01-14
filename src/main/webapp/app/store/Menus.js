Ext.define('CALHUB.store.Menus', {
    extend: 'Ext.data.TreeStore',
    model: 'CALHUB.model.Menu',
    autoLoad: true,

    proxy: {
        type: 'rest',
        url: 'data/menus',
        reader: {
            type: 'json',
            // json 자료구조에서 해당 응답에 menu값을 가지는 키값
            root: 'menu',
            //successProperty: 'success'
        }
    },
    root: {
        // url호출시 루트요청에 대한 url
        // 즉, /data/menus/root를 통한 요청이 트리의 루트가 된다.
        id: 'root'
    }
});