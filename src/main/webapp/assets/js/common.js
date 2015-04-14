;
var $ = $ || {};
(function() {
    Element.prototype.on = function(even, listener) {
        if (this.addEventListener) {
            this.addEventListener(even, listener, false);
        } else {
            this.attachEvent('on' + even, listener);
        }
    };

    Event.prototype.cancelDefault = function() {
        if (this.preventDefault) {
            this.preventDefault();
        } else {
            window.event.returnValue = false;
        }
    };

    $.ready = function(callback) {
        window.onload = callback;
    };

    $.each = function(array, callback) {
        if (array && array.length) {
            for (var i = 0; i < array.length; i++) {
                callback.call(array[i], i, array[i]);
            }
        }
    };
})();