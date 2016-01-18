/**
 * Created by gssflyaway on 16/1/18.
 */

$(document).ready(function () {
    //$("#list1").html("京东");

    var rank = [];
    var cName =  [];
    var num = [];
    var total = new Array();

    $.getJSON("/api/multi/association", function (result) {
        $.each(result, function (i, field) {
            var a = $("<a></a>");
            a.attr("href", "#");
            a.attr("class", "list-group-item");
            var b = $("<span></span>");
            b.html(field);
            b.attr("width", "20%");
            //var c = $("<span></span>");
            //c.html(field.cName);
            //c.attr("id", "list1");
            //c.attr("width", "50%");
            //c.css("margin-left", "5%");
            //var d = $("<span></span>");
            //d.attr("class", "pull-right text-muted small");
            //d.html(field.num);
            $("#rankLists").append(a);
            a.append(b);
            //a.append(c);
            //a.append(d);
        })
    })
})
