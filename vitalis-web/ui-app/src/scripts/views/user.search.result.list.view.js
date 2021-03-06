// main.layout.view.js

'use strict';

App.module('Vitalis.Views', function (Views, App, Backbone, Marionette, $, _) {

    var Urls        = App.module('Urls'),
        Header      = App.module('Header'),
        Vitalis     = App.module('Vitalis');

    Views.FollowRequestUserSearchResultListView = Views.AbstractListView.extend({
        childView: App.Vitalis.Views.FollowRequestUserSearchResultItemView
    });
});