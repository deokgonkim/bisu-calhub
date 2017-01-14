/**
 * Model::CalendarEvent
 * 캘린더 일정 항목을 정의한다.
 */
Ext.define('CALHUB.model.CalendarEvent', {
    extend: 'Ext.data.Model',
    fields:
        [
            {
                name: 'uid',
                type: 'string'
            },{
                name: 'summary',
                fields: [
                    {name: 'name', type: 'string'},
                    {name: 'value', type: 'value'}
                ],
                convert: function(v, record) {
                    return v.value !== undefined ? v.value : v;
                }
            },{
                name: 'dateStamp',
                fields: [
                    {name: 'date', type: 'int'},
                    {name: 'dateTime', type: 'int'},
                    {name: 'name', type: 'string'},
                    {name: 'value', type: 'string'}
                ],
                convert: function(v, record) {
                    return v.date !== undefined ? v.date : v;
                }
            },{
                name: 'organizer',
                fields: [
                    {name: 'name', type: 'string'},
                    {name: 'value', type: 'string'},
                    {name: 'calAddress', type: 'string'},
                ],
                convert: function(v, record) {
                    return v.value !== undefined ? v.value : v;
                }
            },{
                name: 'location',
                type: 'string',
                convert: function(v, record) {
                    return v == null ? '' : ( v.value !== undefined ? v.value : v );
                }
            },{
                name: 'startDate',
                fields: [
                    {name: 'name', type: 'string'},
                    {name: 'date', type: 'int'},
                    {name: 'value', type: 'string'}
                ],
                convert: function(v, record) {
                    return v.date !== undefined ? v.date : v;
                }
            },{
                name: 'endDate',
                fields: [
                    {name: 'name', type: 'string'},
                    {name: 'date', type: 'int'},
                    {name: 'value', type: 'string'}
                ],
                convert: function(v, record) {
                    return v.date !== undefined ? v.date : v;
                }
            },
            'description'
        ]
});