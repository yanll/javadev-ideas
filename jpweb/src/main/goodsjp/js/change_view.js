var change = {
    design_width: 750,
    IsPC: function () {
        var userAgentInfo = navigator.userAgent;
        var Agents = ["Android", "iPhone",
            "SymbianOS", "Windows Phone",
            "iPad",
            "iPod"
        ];
        var flag = true;
        for (var v = 0; v < Agents.length; v++) {
            if (userAgentInfo.indexOf(Agents[v]) > 0) {
                flag = false;
                break;
            }
        }
        return flag;
    },
    pc_type: 0,
    body: document.getElementsByTagName('html')[0],
    action_flag: true,
    action: function (num, $1) {
        change.design_width = num;
        if ($1) {
            change.pc_type = $1;
        }
        if (this.IsPC()) {
            this.view_width = document.documentElement.clientWidth > num ? num : document.documentElement.clientWidth;
            if (this.pc_type || this.pc_type == 1) {
                this.body.className = ' pc_phone';
            } else {
                this.body.className = ' pc';
            }

        } else {
            this.view_width = document.documentElement.clientWidth < 320 ? num / 2 : document.documentElement.clientWidth;
        }
        this.body.style.opacity = 1;
        this.body.style.fontSize = this.view_width * 100 / num + 'px';
        if (change.action_flag) {
            change.action_flag = false;
            window.addEventListener('resize', function () {
                change.action(change.design_width);
            }, false);
        }

    }
}
var tost_tt;
var my = {
    //api: 'http://api1.kxshare.com:82',
    api: 'http://localhost:8080',
    count: 0,
    uid: '',
    title_h: 40,
    scroll: 0,
    system: 'ios',
    post: function (api, obj_json, fn) {
        obj_json.app_token = store.get('app_token');
        $.post(my.api + api, obj_json, function (data) {
            my.hide_loading();
            data = my.json_s(data);
            if (data.token_timeout == 1 && !my.login) {
                location = my.path + '../login/index.html?backurl=' + location.href;
                return false;
            }
            if (data.code == 1000) {
                if (fn) {
                    fn(data);
                }
                window.m_lazy();
            } else {
                my.tips('wrong', data.desc);
            }
        });
    },
    m_lazy: function () {
        var lazy_time = setTimeout(function () {
            window.m_lazy();
            clearTimeout(lazy_time)
        }, 500)
    },
    get: function (api, obj_json, fn) {
        $.get(my.api + api, obj_json, function (data) {
            if (data.code == 1000) {
                if (fn) {
                    fn(data);
                }
                window.m_lazy();
            } else {
                my.tips('wrong', data.desc);
            }
        });
    },
    body_loading: function () {
        $('body').addClass('no_loading');
    },
    count: function ($obj, $_cur, $txt) {
        var tti = setInterval(function () {
            $_cur = $_cur - 10;
            var cur_time = {
                i: Math.floor(($_cur % 1000) / 10),
                s: Math.floor(($_cur % 60000) / 1000),
                m: Math.floor(($_cur % 3600000) / 60000),
                h: Math.floor(($_cur % (3600000 * 60)) / (60 * 60000))
            }
            $obj.html((cur_time.h <= 0 ? '' : (cur_time.h + ':')) + (cur_time.m < 10 ? '0' + cur_time.m : cur_time.m) + ':' + (cur_time.s < 10 ? '0' + cur_time.s : cur_time.s) + ':' + cur_time.i)
            if ($_cur <= 0) {
                clearInterval(tti);
                $obj.html($txt);
                return false;
            }
        }, 10)
    },
    url_attr: function (name) {
        var pattern = new RegExp("[?&]" + name + "\=([^&]+)", "g");
        var matcher = pattern.exec(location.href);
        var items = null;
        if (null != matcher) {
            try {
                items = decodeURIComponent(decodeURIComponent(matcher[1]));
            } catch (e) {
                try {
                    items = decodeURIComponent(matcher[1]);
                } catch (e) {
                    items = matcher[1];
                }
            }
        }
        return items;
    },
    url0_attr: function (name) {
        var pattern = new RegExp("[#&]" + name + "\=([^&]+)", "g");
        var matcher = pattern.exec(location.href);
        var items = null;
        if (null != matcher) {
            try {
                items = decodeURIComponent(decodeURIComponent(matcher[1]));
            } catch (e) {
                try {
                    items = decodeURIComponent(matcher[1]);
                } catch (e) {
                    items = matcher[1];
                }
            }
        }
        return items;
    },
    tips: function (obj1, obj2, fn) {
        var $warn = $('#warn')
        $warn.find('em').html(obj2);
        $warn.find('i')[0].className = "icon icon-" + obj1;
        $warn.show();
        var tt = setTimeout(function () {
            $('#warn').hide();
            if (fn) {
                fn();
            }
            clearTimeout(tt);
        }, 2000);
    },
    prompt: function (html, btn) {
        $prompt = $('#prompt');
        $prompt.find('div.font_content').html(html);
        $prompt.show();
    },
    toast: function (obj) {
        document.getElementById("toast").innerHTML = obj;
        document.getElementById("toast").style.display = 'block';
        tost_tt = setTimeout(function () {
            document.getElementById("toast").style.display = 'none';
        }, 2000)
    },
    json_s: function (obj) {
        if (typeof obj != 'string') {
            return obj
        } else {
            return eval("(" + obj + ")")
        }
    },
    share: function ($jsons) {
        $('<div class="share_body change_fixed"><div class="socials icon_h5"></div></div><section class=erweima><div><img src="http://icon.dyrs.cc/zxsj/blank.gif"><span>微信里点“发现”，扫一下<br>二维码便可将本文分享至朋友圈。</span></div></section>').appendTo('body');
        share_fn();
        $('.socials').share($jsons);
        $('.shares').click(function () {
            $('.share_body').show().find('social-share').addClass('normal');
        })
        $('.social-share').on('click', '.icon-wechat', function () {
            $('.erweima').show();
            $('.erweima img').attr('src', $(this).find('.wechat-qrcode img').attr('src'))

        });
        $('.erweima div,.social-share').click(function (e) {
            e.stopPropagation();
        })
        $('.erweima,.share_body').click(function () {
            $(this).hide();
        })
    },
    email: /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/,
    tel_test: /^1[3|4|5|7|8][0-9]\d{8,8}$/,
    regPhone: /^(^0\d{2}-?\d{8}$)|(^0\d{3}-?\d{7}$)|(^\(0\d{2}\)-?\d{8}$)|(^\(0\d{3}\)-?\d{7}$)$/,
    scroll_ajax_flag: true,
    page: function (txt, ajax_fn) {
        var $win = $(window);
        var $txt = $(txt);
        $win.scroll(function () {
            if ($win.scrollTop() + 100 > $txt.offset().top - $win.height()) {
                if (my.scroll_ajax_flag) {
                    $txt.html(my.load[0]);
                    ajax_fn();
                }
            }
        });
        window.m_lazy();
    },
    img: function () {
        template.helper('img', function (obj, num, type) {
            if (obj != '' && obj != null && obj.slice(-4, -3) != '!') {
                return obj + '!' + num;
            } else {
                if (type == 1 && (obj == '' || obj == null)) { //1为脑袋
                    var img_Url = 'http://img.hotyq.com/icon/user/defaulthead.jpg' + '!' + num; //默认头像
                } else {
                    var img_Url = obj;
                }
                return img_Url;
            }
        })
    },
    need_login_flag: true,
    load: ['正在加载中^_^', '加载已完成！'],
    title_choose: function () {
        $('.left_right span').each(function (index, obj) {
            this.index = index;
            $(this).click(function () {
                $(this).addClass('on').siblings().removeClass('on')
                $(this).parents('section').next().children('div').hide().eq(this.index).show();
            });
        });
        $('.title_choose_shape section.title span').each(function (index, obj) {
            this.index = index;
            $(this).click(function () {
                if ($(this).hasClass('on')) {
                    $(this).removeClass('on');
                    $(this).parents('.title_choose_shape').find('.title_choose_content').hide()
                    return false;
                }
                $(this).addClass('on').siblings().removeClass('on');
                $(this).parents('.title_choose_shape').children('.title_choose_content').show().find('.choose_content_list').hide().eq(this.index).show();
            })
        });
        $('.stop_p').click(function (e) {
            e.stopPropagation();
        })
        $('.title_choose_content').click(function () {
            $(this).prev().find('span.on').click();
        });
    },
    show_loading: function () {
        $('#ajax_loading').show();
    },
    hide_loading: function () {
        $('#ajax_loading').hide();
    },
    view_no_scroll: function () {
        my.scroll = $(window).scrollTop();
        $('html,body').addClass('no_scroll');
    },
    view_scroll: function () {
        $('html,body').removeClass('no_scroll');
        $(window).scrollTop(my.scroll)
        my.scroll = 0;
    },
    z_time: function ($txt) {
        var $all_time = $('[data-cur]');
        $all_time.each(function (index, obj) {
            var $obj = $(obj);
            var _cur = Number($obj.attr('data-cur'));
            $obj.removeAttr('data-cur');
            my.count($obj, _cur, $txt ? $txt : '00:00:00')
        });
    },
    z_data: function (time_data) {
        var timestamp = Number(time_data);
        var n = new Date();
        var d = new Date(timestamp); //根据时间戳生成的时间对象
        var date = (d.getFullYear()) + "-" +
            (d.getMonth() + 1) + "-" +
            (d.getDate()) + "," +
            (d.getHours()) + ":" +
            ((d.getMinutes()) < 10 ? "0" + (d.getMinutes()) : d.getMinutes()) + ":" +
            (d.getSeconds() < 10 ? '0' + d.getSeconds() : d.getSeconds());
        var date1 = (n.getFullYear()) + "-" +
            (n.getMonth() + 1) + "-" +
            (n.getDate());
        if (date1 == date.split(',')[0]) {
            date = '今天 ' + date.split(',')[1];
        } else {
            date = date.split(',')[0] + ' ' + date.split(',')[1]
        }
        return date;
    },
    down: function () {
        if (my.url_attr('view_app') != true) {
            $('<section id="download"></section>').prependTo('body').load(my.path + 'download.html', function () {
            });
        }
    },
    public: function (path, type, objs) {
        my.path = path;
        //json_string/s
        template.helper('json', function ($json) {
            return JSON.stringify($json);
        });
        //json_string/e
        //需要登录判断/s
        my.customer_id = store.get('customer_id');
        if ($('body').hasClass('login_return')) {
            if (!my.customer_id) {
                location.href = path + '../login/index.html?backurl=' + location.href;
            }
        }
        //需要登录判断/e
        //右上角角标99+/s
        template.helper('right_bar', function ($num) {
            if ($num > 99) {
                return '99+';
            } else {
                return $num;
            }
        });
        //右上角角标99+/e
        //时间模板/s
        template.helper('time_z', function (time_data) {
            return my.z_data(time_data);
        });
        //时间模板/e
        //倒计时方法/s
        template.helper('time', function (_now, _end) {
            return _now - _end;
        });
        //倒计时/e
        //头像/s
        template.helper('head_img', function (head) {
            if (head) {
                return head;
            } else {
                return 'http://7xkwkh.com1.z0.glb.clouddn.com/ios.png';
            }
        });
        //头像/e
        //1:首页2：搜索结果页面3:search右边没有4:返回 标题 购物车5:标题 返回6：只有标题7：个人中心
        if (type == 1) {
            var now_class = "index";
        } else if (type == 2) {
            var now_class = "search";
        } else if (type == 3) {
            var now_class = 'search1';
        } else if (type == 4) {
            var now_class = 'info';
        } else if (type == 5) {
            var now_class = 'normal'
        } else if (type == 6) {
            var now_class = 'only'
        } else if (type == 7) {
            var now_class = 'nono'
        }
        ;
        if ($('body').hasClass('has_meun')) {
            $('<section class="meun_footer box border_t change_fixed">').prependTo('body').load(path + 'menu.html', function () {
                var $footer_a = $('.meun_footer a');
                $footer_a.removeClass('on').eq($('body').attr('data-meun')).addClass('on');
                $footer_a.each(function (index, obj) {
                    $(this).attr('href', path + $(this).attr('href'));
                    var $json_list = store.get('json_list');
                    if ($json_list) {
                        $json_list = JSON.parse($json_list);
                        $('.right_list_bar').attr('data-rightbar', $json_list.data.length);
                    }
                });
            });
        }
        $('body').on('input', 'input', function (e) {
            if ($(this).parent().has('input_close')) {
                $(this).next('em').show()
            }
        }).on('click', 'em.icon-close', function (e) {
            if ($(this).parent().has('input_close')) {
                $(this).hide().prev('input').val('');
            }
        }).on('click', 'a.view_app', function () {
            if (my.url_attr('view_app') == 'true') {
                var _this = $(this);
                if (location.href.indexOf('?') >= 0) {
                    _this.attr('href', _this.attr('href') + '&view_app=true')
                } else {
                    _this.attr('href', _this.attr('href') + '?view_app=true')
                }
            }
        });
        if (type != 8) {
            $('<section id="public_header" class=' + now_class + '>').prependTo('body').load(path + 'header.html', function () {
                if (objs) {
                    if (objs.title_fn) {
                        objs.title_fn();
                    }
                }
                ;
                var $json_list = store.get('json_list');
                if ($json_list) {
                    $json_list = JSON.parse($json_list);
                    $('.right_list_bar').attr('data-rightbar', $json_list.data.length);
                }
                ;
            });
        }
        //添加loading模块/S
        $("<section id='ajax_loading'><div><i></i></div></section>").appendTo('body');
        document.getElementById("ajax_loading").addEventListener('touchmove', function (e) {
            e.preventDefault();
        }, false);
        //添加loading模块/E
        $('<div class="go_top icon icon-top"></div>').appendTo('body').click(function () {
            $('html,body').stop().animate({
                scrollTop: 0
            }, 500);
        }); //返回顶部
        var $go_top = $('.go_top');
        $(window).scroll(function () {
            if ($(this).scrollTop() > 200) {
                $go_top.show();
            } else {
                $go_top.hide();
            }
        });
        $('body').on('click', '.need_search', function () {
            if (!my.search) {
                my.show_loading();
                $('<section id="public_search" class="change_fixed">').prependTo('body').load(path + 'search.html', function () {
                    my.search = true;
                    my.hide_loading();
                    $('#public_search').show();
                    my.view_no_scroll();
                    $('.form_search').attr('action', path + '../search/index.html')
                });
            } else {
                $('#public_search').show();
                my.view_no_scroll();
            }
        })
        //警告框
        $('<section id="warn" class="change_fixed no_touch"><span class="warn"><i class=" icon"></i><em></em></span></section>').appendTo('body');
        //prompt
        $('<section id="prompt" class="change_fixed no_touch"><div class="middle_center"><div class="font_content"></div><button>确定</button></div></section>').appendTo('body').find('button').click(function () {
            $(this).parents('#prompt').hide();
        });

        $('.no_touch').each(function (index, obj) {
            obj.addEventListener('touchmove', function (e) {
                e.preventDefault();
            }, false);
        });
        //登录/s
        $('body').on('click', '.need_login', function (e) {
            if (!store.get('customer_id')) {
                e.preventDefault();
                if ($(this).attr('href') != undefined) {
                    location.href = path + '../login/index.html?backurl=' + location.href + '/../' + $(this).attr('href');
                } else {
                    location.href = path + '../login/index.html?backurl=' + location.href;
                }
            }

        });
        if (my.url_attr('backurl')) {
            $('body').on('click', '.need_back_url', function () {
                $(this).attr('href', $(this).attr('href') + '?backurl=' + my.url_attr('backurl'))
            })
        }
        //登录/e
        //path 地址/e
        $('body').on('click', '.need_path', function () {
            $(this).attr('href', path + $(this).attr('href'))
        });
        //path 地址/e
    },
    lay_flex: function (obj, css1, css2) {
        var $obj = $(obj);
        var start_top = $obj.offset().top;
        $(window).scroll(function () {
            var $win = $(window);
            if ($win.scrollTop() > start_top - my.title_h) {
                $obj.css(css2);
            } else {
                $obj.css(css1);
            }
        })
    }
}
if (!(navigator.userAgent).match(/\(i[^;]+;( U;)? CPU.+Mac OS X/)) {
    my.system = 'android';
}
window.addEventListener('load', function () {
    var toast = document.createElement("section");
    toast.id = 'toast';
    document.querySelector('body').appendChild(toast);
});

(function () {
    Array.prototype.indexOf = function (val) {
        for (var i = 0; i < this.length; i++) {
            if (this[i] == val) return i;
        }
        return -1;
    };
    Array.prototype.remove = function (val) {
        var index = this.indexOf(val);
        if (index > -1) {
            this.splice(index, 1);
        }
    };
})();
