import $ from 'jquery';

export default {

  getMotd: function(then) {
    $.ajax({
      method: "GET",
      url: `${conf.server}/motd`,
      success: then
    });
  }

}