/**
 * Model::Menu
 * 메뉴구조를 정의한다.
 */
Ext.define('CALHUB.model.Menu', {
    extend: 'Ext.data.Model',
    fields: [ 'id', 'text', 'leaf', 'viewName', 'menuDescription', 'url' ]
});