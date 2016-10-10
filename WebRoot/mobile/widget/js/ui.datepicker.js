!
function(a) {
    "function" == typeof define && define.amd ? define(["jquery"], a) : a(jQuery)
}(function(a) {
    "use strict";
    var b = a(window),
    c = a(document),
    d = function(b, c) {
        this.$element = a(b), this.defaults = a.extend({}, d.defaults, this.$element.data(), a.isPlainObject(c) ? c : {}), this.init()
    };
    d.prototype = {
        constructor: d,
        init: function() {
            var b = this.defaults.trigger;
            this.$trigger = b ? a(b) : this.$element, this.$picker = a(this.defaults.template), this.$years = this.$picker.find("[data-type='years picker']"), this.$months = this.$picker.find("[data-type='months picker']"), this.$days = this.$picker.find("[data-type='days picker']"), this.$picker.appendTo("body"), this.place(), this.hide(), this.format = d.fn.parseFormat(this.defaults.dateFormat), this.fillWeek(), this.enable()
        },
        enable: function() {
            this.enabled || (this.$element.is("input") && (this.$element.on("keyup", a.proxy(this.update, this)), this.defaults.trigger || this.$element.on("focus", a.proxy(this.show, this))), this.$trigger.on("click", a.proxy(this.show, this)), this.$picker.on({
                click: a.proxy(this.click, this),
                mousedown: a.proxy(this.mousedown, this)
            }), this.update(), this.enabled = !0)
        },
        disable: function() {
            this.enabled && (this.$element.is("input") && (this.$element.off("keyup", this.update), this.defaults.trigger || this.$element.off("focus", this.show)), this.$trigger.off("click", this.show), this.$picker.off({
                click: this.click,
                mousedown: this.mousedown
            }), this.hide(), this.enabled = !1)
        },
        showView: function(a) {
            var b = this.format;
            if (b.year || b.month || b.day) switch (a) {
                case 2:
                case "years":
                this.$months.hide(), this.$days.hide(), b.year ? (this.fillYears(), this.$years.show()) : this.showView(0);
                break;
                case 1:
                case "months":
                this.$years.hide(), this.$days.hide(), b.month ? (this.fillMonths(), this.$months.show()) : this.showView(2);
                break;
                default:
                this.$years.hide(), this.$months.hide(), b.day ? (this.fillDays(), this.$days.show()) : this.showView(1)
            }
        },
        hideView: function() {
            this.defaults.autoClose && this.hide()
        },
        place: function() {
            var a = this.$trigger.offset(),
            b = this.$trigger.outerHeight();

            this.$picker.css({
                top: '50%',
                left: '50%'
            })
        },
        show: function() {
            this.enabled && (this.$picker.show(), b.on("resize", a.proxy(this.place, this)), c.on("mousedown", a.proxy(this.hide, this)), this.place(), this.showView(this.defaults.viewStart))
        },
        hide: function() {
            this.$picker.hide(), b.off("resize", this.place), c.off("mousedown", this.hide)
        },
        mousedown: function(a) {
            a.stopPropagation(), a.preventDefault()
        },
        update: function() {
            var a = this.$element.is("input") ? this.$element.prop("value") : this.$element.text();
            this.date = d.fn.parseDate(a, this.format), this.viewDate = new Date(this.date.getFullYear(), this.date.getMonth(), this.date.getDate(), 0, 0, 0, 0), this.fillAll()
        },
        output: function() {
            var a = this.$element,
            b = d.fn.formatDate(this.date, this.format);
            a.is("input") ? a.prop("value", b).trigger("change") : a.text(b)
        },
        template: function(b) {
            var c = {
                text: "",
                type: "",
                selected: !1,
                disabled: !1
            };
            return a.extend(c, b), ["<" + this.defaults.itemTag + " ", c.selected ? 'class="' + this.defaults.selectedClass + '"' : c.disabled ? 'class="' + this.defaults.disabledClass + '"' : "", c.type ? ' data-type="' + c.type + '"' : "", ">", c.text, "</" + this.defaults.itemTag + ">"].join("")
        },
        fillAll: function() {
            this.fillYears(), this.fillMonths(), this.fillDays()
        },
        fillYears: function() {
            var a, b, c = "",
            d = [],
            e = this.defaults.yearSuffix || "",
            f = this.date.getFullYear(),
            g = this.viewDate.getFullYear();
            for (c = g - 5 + e + " - " + (g + 6) + e, b = -5; 7 > b; b++) a = g + b === f, d.push(this.template({
                text: g + b,
                type: a ? "year selected" : "year",
                selected: a,
                disabled: -5 === b || 6 === b
            }));
                this.$picker.find("[data-type='years current']").html(c), this.$picker.find("[data-type='years']").empty().html(d.join(""))
            },
            fillMonths: function() {
                var a, b, c = "",
                d = [],
                e = this.defaults.monthsShort,
                f = this.date.getFullYear(),
                g = this.date.getMonth(),
                h = this.viewDate.getFullYear();
                for (c = h.toString() + this.defaults.yearSuffix || "", b = 0; 12 > b; b++) a = h === f && b === g, d.push(this.template({
                    text: e[b],
                    type: a ? "month selected" : "month",
                    selected: a
                }));
                    this.$picker.find("[data-type='year current']").html(c), this.$picker.find("[data-type='months']").empty().html(d.join(""))
                },
                fillWeek: function() {
                    var b, c = [],
                    d = this.defaults.daysMin,
                    e = parseInt(this.defaults.weekStart, 10) % 7;
                    for (d = a.merge(d.slice(e), d.slice(0, e)), b = 0; 7 > b; b++) c.push(this.template({
                        text: d[b]
                    }));
                        this.$picker.find("[data-type='week']").empty().html(c.join(""))
                    },
                    fillDays: function() {
                        var b, c, e, f, g, h, i = "",
                        j = [],
                        k = [],
                        l = [],
                        m = [],
                        n = this.defaults.monthsShort,
                        o = this.defaults.yearSuffix || "",
                        p = this.date.getFullYear(),
                        q = this.date.getMonth(),
                        r = this.date.getDate(),
                        s = this.viewDate.getFullYear(),
                        t = this.viewDate.getMonth(),
                        u = parseInt(this.defaults.weekStart, 10) % 7;
                        for (i = this.defaults.showMonthAfterYear ? s + o + " " + n[t] : n[t] + " " + s + o, e = 0 === t ? d.fn.getDaysInMonth(s - 1, 11) : d.fn.getDaysInMonth(s, t - 1), g = 1; e >= g; g++) k.push(this.template({
                            text: g,
                            type: "day prev",
                            disabled: !0
                        }));
                            for (f = new Date(s, t, 1, 0, 0, 0, 0), h = (7 + (f.getDay() - u)) % 7, h = h > 0 ? h : 7, k = k.slice(e - h), e = 11 === t ? d.fn.getDaysInMonth(s + 1, 0) : d.fn.getDaysInMonth(s, t + 1), g = 1; e >= g; g++) m.push(this.template({
                                text: g,
                                type: "day next",
                                disabled: !0
                            }));
                                for (e = d.fn.getDaysInMonth(s, t), f = new Date(s, t, e, 0, 0, 0, 0), h = (7 - (f.getDay() + 1 - u)) % 7, h = h >= 42 - (k.length + e) ? h : h + 7, m = m.slice(0, h), g = 1; e >= g; g++) b = s === p && t === q && g === r, c = this.defaults.isDisabled(new Date(s, t, g)), l.push(this.template({
                                    text: g,
                                    type: c ? "day disabled" : b ? "day selected" : "day",
                                    selected: b,
                                    disabled: c
                                }));
                                    a.merge(j, k), a.merge(j, l), a.merge(j, m), this.$picker.find("[data-type='month current']").html(i), this.$picker.find("[data-type='days']").empty().html(j.join(""))
                                },
                                click: function(b) {
                                    var c, d, e, f, g, h = a(b.target),
                                    i = /^\d{2,4}$/,
                                    j = !1;
                                    if (b.stopPropagation(), b.preventDefault(), 0 !== h.length) switch (c = this.viewDate.getFullYear(), d = this.viewDate.getMonth(), e = this.viewDate.getDate(), g = h.data().type) {
                                        case "years prev":
                                        case "years next":
                                        c = "years prev" === g ? c - 10 : c + 10, f = h.text(), j = i.test(f), j && (c = parseInt(f, 10), this.date = new Date(c, d, Math.min(e, 28), 0, 0, 0, 0)), this.viewDate = new Date(c, d, Math.min(e, 28), 0, 0, 0, 0), this.fillYears(), j && (this.showView(1), this.output());
                                        break;
                                        case "year prev":
                                        case "year next":
                                        c = "year prev" === g ? c - 1 : c + 1, this.viewDate = new Date(c, d, Math.min(e, 28), 0, 0, 0, 0), this.fillMonths();
                                        break;
                                        case "year current":
                                        this.format.year && this.showView(2);
                                        break;
                                        case "year selected":
                                        this.format.month ? this.showView(1) : this.hideView();
                                        break;
                                        case "year":
                                        c = parseInt(h.text(), 10), this.date = new Date(c, d, Math.min(e, 28), 0, 0, 0, 0), this.viewDate = new Date(c, d, Math.min(e, 28), 0, 0, 0, 0), this.format.month ? this.showView(1) : this.hideView(), this.output();
                                        break;
                                        case "month prev":
                                        case "month next":
                                        d = "month prev" === g ? d - 1 : "month next" === g ? d + 1 : d, this.viewDate = new Date(c, d, Math.min(e, 28), 0, 0, 0, 0), this.fillDays();
                                        break;
                                        case "month current":
                                        this.format.month && this.showView(1);
                                        break;
                                        case "month selected":
                                        this.format.day ? this.showView(0) : this.hideView();
                                        break;
                                        case "month":
                                        d = h.parent().children().index(h), this.date = new Date(c, d, Math.min(e, 28), 0, 0, 0, 0), this.viewDate = new Date(c, d, Math.min(e, 28), 0, 0, 0, 0), this.format.day ? this.showView(0) : this.hideView(), this.output();
                                        break;
                                        case "day prev":
                                        case "day next":
                                        case "day":
                                        d = "day prev" === g ? d - 1 : "day next" === g ? d + 1 : d, e = parseInt(h.text(), 10), this.date = new Date(c, d, e, 0, 0, 0, 0), this.viewDate = new Date(c, d, e, 0, 0, 0, 0), this.fillDays(), "day" === g && this.hideView(), this.output();
                                        break;
                                        case "day selected":
                                        this.hideView(), this.output();
                                        break;
                                        case "day disabled":
                                        this.hideView()
                                    }
                                }
                            }, d.fn = {
                                isLeapYear: function(a) {
                                    return a % 4 === 0 && a % 100 !== 0 || a % 400 === 0
                                },
                                getDaysInMonth: function(a, b) {
                                    return [31, this.isLeapYear(a) ? 29 : 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31][b]
                                },
                                parseFormat: function(a) {
                                    var b, c, d = a.match(/[.\/\-\s].*?/) || "/",
                                    e = a.split(/\W+/);
                                    if (!e || 0 === e.length) throw new Error("Invalid date format.");
                                    for (a = {
                                        separator: d[0],
                                        parts: e
                                    }, c = 0, b = e.length; b > c; c++) switch (e[c]) {
                                        case "dd":
                                        case "d":
                                        a.day = !0;
                                        break;
                                        case "mm":
                                        case "m":
                                        a.month = !0;
                                        break;
                                        case "yyyy":
                                        case "yy":
                                        a.year = !0
                                    }
                                    return a
                                },
                                parseDate: function(a, b) {
                                    var c, d, e, f, g, h, i;
                                    if (c = "string" == typeof a && a.length > 0 ? a.split(b.separator) : [], d = b.parts.length, a = new Date, e = a.getFullYear(), f = a.getDate(), g = a.getMonth(), c.length === d) for (i = 0; d > i; i++) switch (h = parseInt(c[i], 10) || 1, b.parts[i]) {
                                        case "dd":
                                        case "d":
                                        f = h;
                                        break;
                                        case "mm":
                                        case "m":
                                        g = h - 1;
                                        break;
                                        case "yy":
                                        e = 2e3 + h;
                                        break;
                                        case "yyyy":
                                        e = h
                                    }
                                    return new Date(e, g, f, 0, 0, 0, 0)
                                },
                                formatDate: function(a, b) {
                                    var c, d = {
                                        d: a.getDate(),
                                        m: a.getMonth() + 1,
                                        yy: a.getFullYear().toString().substring(2),
                                        yyyy: a.getFullYear()
                                    },
                                    e = [],
                                    f = b.parts.length;
                                    for (d.dd = (d.d < 10 ? "0" : "") + d.d, d.mm = (d.m < 10 ? "0" : "") + d.m, c = 0; f > c; c++) e.push(d[b.parts[c]]);
                                        return e.join(b.separator)
                                }
                            }, d.defaults = {
                                autoClose: !1,
                                dateFormat: "mm/dd/yyyy",
                                days: ["Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"],
                                daysShort: ["Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"],
                                daysMin: ["Su", "Mo", "Tu", "We", "Th", "Fr", "Sa", "Su"],
                                disabledClass: "disabled",
                                isDisabled: function() {
                                    return !1
                                },
                                itemTag: "li",
                                months: ["January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"],
                                monthsShort: ["Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"],
                                selectedClass: "selected",
                                showMonthAfterYear: !1,
                                template: ['<div class="datepicker-container" data-type="datepicker">', '<div class="datepickerMask"></div>', '<div class="datepicker-arrow"></div>', '<div class="datepicker-content">', '<div class="content-years" data-type="years picker">', '<ul class="datepicker-title">', '<li class="datepicker-prev" data-type="years prev">&lsaquo;</li>', '<li class="col-5" data-type="years current"></li>', '<li class="datepicker-next" data-type="years next">&rsaquo;</li>', "</ul>", '<ul class="datepicker-years" data-type="years"></ul>', "</div>", '<div class="content-months" data-type="months picker">', '<ul class="datepicker-title">', '<li class="datepicker-prev" data-type="year prev">&lsaquo;</li>', '<li class="col-5" data-type="year current"></li>', '<li class="datepicker-next" data-type="year next">&rsaquo;</li>', "</ul>", '<ul class="datepicker-months" data-type="months"></ul>', "</div>", '<div class="content-days" data-type="days picker">', '<ul class="datepicker-title">', '<li class="datepicker-prev" data-type="month prev">&lsaquo;</li>', '<li class="col-5" data-type="month current"></li>', '<li class="datepicker-next" data-type="month next">&rsaquo;</li>', "</ul>", '<ul class="datepicker-week" data-type="week"></ul>', '<ul class="datepicker-days" data-type="days"></ul>', "</div>", "</div>","</div>"].join(""),
                                trigger: void 0,
                                viewStart: 0,
                                weekStart: 0,
                                yearSuffix: ""
                            }, d.setDefaults = function(b) {
                                a.extend(d.defaults, b)
                            }, a.fn.datepicker = function(b) {
                                return this.each(function() {
                                    var c = a(this),
                                    e = c.data("datepicker");
                                    e || (e = new d(this, b), c.data("datepicker", e)), "string" == typeof b && a.isFunction(e[b]) && e[b]()
                                })
                            }, a.fn.datepicker.constructor = d, a.fn.datepicker.setDefaults = d.setDefaults, a(function() {
                                a("[datepicker]").datepicker()
                            })
                        });
$.fn.datepicker.setDefaults({
    autoClose: false,
    dateFormat: "yyyy-mm-dd",
    days: ["星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"],
    daysShort: ["周日", "周一", "周二", "周三", "周四", "周五", "周六"],
    daysMin: ["日", "一", "二", "三", "四", "五", "六"],
    months: ["一月", "二月", "三月", "四月", "五月", "六月", "七月", "八月", "九月", "十月", "十一月", "十二月"],
    monthsShort: ["1月", "2月", "3月", "4月", "5月", "6月", "7月", "8月", "9月", "10月", "11月", "12月"],
    showMonthAfterYear: true,
    viewStart: 0,
    weekStart: 1,
    yearSuffix: "年"
});
$(function(){
    $('.datepickerMask').on('click', function(event) {
       $('.datepicker-container').hide();
    });
    $('.cinp-date').focus(function(event) {
       $(this).blur();
    });
})