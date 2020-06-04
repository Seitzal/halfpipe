const api = {

  getMotd: function(then) {
    $.ajax({
      method: "GET",
      url: `${config.app.location}/motd`,
      success: then
    });
  }

};