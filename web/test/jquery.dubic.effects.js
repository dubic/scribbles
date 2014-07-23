/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var Deffects = {
    hoverDisplay: function(onHover, toShow) {
        $(toShow).hide();
        $(onHover).hover(function() {
            $(toShow).show();
        }, function() {
            $(toShow).hide();
        });
    },
    transitionFade: function(t_old, t_new, dur) {
        t_old.fadeOut(dur, function() {
            t_new.fadeIn(dur);
        });
    },
    popover: function() {
        
    }
};
