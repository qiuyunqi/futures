var swfobject = function() {
	var D = "undefined", r = "object", S = "Shockwave Flash", W = "ShockwaveFlash.ShockwaveFlash", q = "application/x-shockwave-flash", R = "SWFObjectExprInst", x = "onreadystatechange", O = window, j = document, t = navigator, T = false, U = [ h ], o = [], N = [], I = [], l, Q, E, B, J = false, a = false, n, G, m = true, M = function() {
		var aa = typeof j.getElementById != D
				&& typeof j.getElementsByTagName != D
				&& typeof j.createElement != D, ah = t.userAgent.toLowerCase(), Y = t.platform
				.toLowerCase(), ae = Y ? /win/.test(Y) : /win/.test(ah), ac = Y ? /mac/
				.test(Y)
				: /mac/.test(ah), af = /webkit/.test(ah) ? parseFloat(ah
				.replace(/^.*webkit\/(\d+(\.\d+)?).*$/, "$1")) : false, X = !+"\v1", ag = [
				0, 0, 0 ], ab = null;
		if (typeof t.plugins != D && typeof t.plugins[S] == r) {
			ab = t.plugins[S].description;
			if (ab
					&& !(typeof t.mimeTypes != D && t.mimeTypes[q] && !t.mimeTypes[q].enabledPlugin)) {
				T = true;
				X = false;
				ab = ab.replace(/^.*\s+(\S+\s+\S+$)/, "$1");
				ag[0] = parseInt(ab.replace(/^(.*)\..*$/, "$1"), 10);
				ag[1] = parseInt(ab.replace(/^.*\.(.*)\s.*$/, "$1"), 10);
				ag[2] = /[a-zA-Z]/.test(ab) ? parseInt(ab.replace(
						/^.*[a-zA-Z]+(.*)$/, "$1"), 10) : 0
			}
		} else {
			if (typeof O.ActiveXObject != D) {
				try {
					var ad = new ActiveXObject(W);
					if (ad) {
						ab = ad.GetVariable("$version");
						if (ab) {
							X = true;
							ab = ab.split(" ")[1].split(",");
							ag = [ parseInt(ab[0], 10), parseInt(ab[1], 10),
									parseInt(ab[2], 10) ]
						}
					}
				} catch (Z) {
				}
			}
		}
		return {
			w3 : aa,
			pv : ag,
			wk : af,
			ie : X,
			win : ae,
			mac : ac
		}
	}(), k = function() {
		if (!M.w3) {
			return
		}
		if ((typeof j.readyState != D && j.readyState == "complete")
				|| (typeof j.readyState == D && (j.getElementsByTagName("body")[0] || j.body))) {
			f()
		}
		if (!J) {
			if (typeof j.addEventListener != D) {
				j.addEventListener("DOMContentLoaded", f, false)
			}
			if (M.ie && M.win) {
				j.attachEvent(x, function() {
					if (j.readyState == "complete") {
						j.detachEvent(x, arguments.callee);
						f()
					}
				});
				if (O == top) {
					(function() {
						if (J) {
							return
						}
						try {
							j.documentElement.doScroll("left")
						} catch (X) {
							setTimeout(arguments.callee, 0);
							return
						}
						f()
					})()
				}
			}
			if (M.wk) {
				(function() {
					if (J) {
						return
					}
					if (!/loaded|complete/.test(j.readyState)) {
						setTimeout(arguments.callee, 0);
						return
					}
					f()
				})()
			}
			s(f)
		}
	}();
	function f() {
		if (J) {
			return
		}
		try {
			var Z = j.getElementsByTagName("body")[0].appendChild(C("span"));
			Z.parentNode.removeChild(Z)
		} catch (aa) {
			return
		}
		J = true;
		var X = U.length;
		for ( var Y = 0; Y < X; Y++) {
			U[Y]()
		}
	}
	function K(X) {
		if (J) {
			X()
		} else {
			U[U.length] = X
		}
	}
	function s(Y) {
		if (typeof O.addEventListener != D) {
			O.addEventListener("load", Y, false)
		} else {
			if (typeof j.addEventListener != D) {
				j.addEventListener("load", Y, false)
			} else {
				if (typeof O.attachEvent != D) {
					i(O, "onload", Y)
				} else {
					if (typeof O.onload == "function") {
						var X = O.onload;
						O.onload = function() {
							X();
							Y()
						}
					} else {
						O.onload = Y
					}
				}
			}
		}
	}
	function h() {
		if (T) {
			V()
		} else {
			H()
		}
	}
	function V() {
		var X = j.getElementsByTagName("body")[0];
		var aa = C(r);
		aa.setAttribute("type", q);
		var Z = X.appendChild(aa);
		if (Z) {
			var Y = 0;
			(function() {
				if (typeof Z.GetVariable != D) {
					var ab = Z.GetVariable("$version");
					if (ab) {
						ab = ab.split(" ")[1].split(",");
						M.pv = [ parseInt(ab[0], 10), parseInt(ab[1], 10),
								parseInt(ab[2], 10) ]
					}
				} else {
					if (Y < 10) {
						Y++;
						setTimeout(arguments.callee, 10);
						return
					}
				}
				X.removeChild(aa);
				Z = null;
				H()
			})()
		} else {
			H()
		}
	}
	function H() {
		var ag = o.length;
		if (ag > 0) {
			for ( var af = 0; af < ag; af++) {
				var Y = o[af].id;
				var ab = o[af].callbackFn;
				var aa = {
					success : false,
					id : Y
				};
				if (M.pv[0] > 0) {
					var ae = c(Y);
					if (ae) {
						if (F(o[af].swfVersion) && !(M.wk && M.wk < 312)) {
							w(Y, true);
							if (ab) {
								aa.success = true;
								aa.ref = z(Y);
								ab(aa)
							}
						} else {
							if (o[af].expressInstall && A()) {
								var ai = {};
								ai.data = o[af].expressInstall;
								ai.width = ae.getAttribute("width") || "0";
								ai.height = ae.getAttribute("height") || "0";
								if (ae.getAttribute("class")) {
									ai.styleclass = ae.getAttribute("class")
								}
								if (ae.getAttribute("align")) {
									ai.align = ae.getAttribute("align")
								}
								var ah = {};
								var X = ae.getElementsByTagName("param");
								var ac = X.length;
								for ( var ad = 0; ad < ac; ad++) {
									if (X[ad].getAttribute("name")
											.toLowerCase() != "movie") {
										ah[X[ad].getAttribute("name")] = X[ad]
												.getAttribute("value")
									}
								}
								P(ai, ah, Y, ab)
							} else {
								p(ae);
								if (ab) {
									ab(aa)
								}
							}
						}
					}
				} else {
					w(Y, true);
					if (ab) {
						var Z = z(Y);
						if (Z && typeof Z.SetVariable != D) {
							aa.success = true;
							aa.ref = Z
						}
						ab(aa)
					}
				}
			}
		}
	}
	function z(aa) {
		var X = null;
		var Y = c(aa);
		if (Y && Y.nodeName == "OBJECT") {
			if (typeof Y.SetVariable != D) {
				X = Y
			} else {
				var Z = Y.getElementsByTagName(r)[0];
				if (Z) {
					X = Z
				}
			}
		}
		return X
	}
	function A() {
		return !a && F("6.0.65") && (M.win || M.mac) && !(M.wk && M.wk < 312)
	}
	function P(aa, ab, X, Z) {
		a = true;
		E = Z || null;
		B = {
			success : false,
			id : X
		};
		var ae = c(X);
		if (ae) {
			if (ae.nodeName == "OBJECT") {
				l = g(ae);
				Q = null
			} else {
				l = ae;
				Q = X
			}
			aa.id = R;
			if (typeof aa.width == D
					|| (!/%$/.test(aa.width) && parseInt(aa.width, 10) < 310)) {
				aa.width = "310"
			}
			if (typeof aa.height == D
					|| (!/%$/.test(aa.height) && parseInt(aa.height, 10) < 137)) {
				aa.height = "137"
			}
			j.title = j.title.slice(0, 47) + " - Flash Player Installation";
			var ad = M.ie && M.win ? "ActiveX" : "PlugIn", ac = "MMredirectURL="
					+ O.location.toString().replace(/&/g, "%26")
					+ "&MMplayerType=" + ad + "&MMdoctitle=" + j.title;
			if (typeof ab.flashvars != D) {
				ab.flashvars += "&" + ac
			} else {
				ab.flashvars = ac
			}
			if (M.ie && M.win && ae.readyState != 4) {
				var Y = C("div");
				X += "SWFObjectNew";
				Y.setAttribute("id", X);
				ae.parentNode.insertBefore(Y, ae);
				ae.style.display = "none";
				(function() {
					if (ae.readyState == 4) {
						ae.parentNode.removeChild(ae)
					} else {
						setTimeout(arguments.callee, 10)
					}
				})()
			}
			u(aa, ab, X)
		}
	}
	function p(Y) {
		if (M.ie && M.win && Y.readyState != 4) {
			var X = C("div");
			Y.parentNode.insertBefore(X, Y);
			X.parentNode.replaceChild(g(Y), X);
			Y.style.display = "none";
			(function() {
				if (Y.readyState == 4) {
					Y.parentNode.removeChild(Y)
				} else {
					setTimeout(arguments.callee, 10)
				}
			})()
		} else {
			Y.parentNode.replaceChild(g(Y), Y)
		}
	}
	function g(ab) {
		var aa = C("div");
		if (M.win && M.ie) {
			aa.innerHTML = ab.innerHTML
		} else {
			var Y = ab.getElementsByTagName(r)[0];
			if (Y) {
				var ad = Y.childNodes;
				if (ad) {
					var X = ad.length;
					for ( var Z = 0; Z < X; Z++) {
						if (!(ad[Z].nodeType == 1 && ad[Z].nodeName == "PARAM")
								&& !(ad[Z].nodeType == 8)) {
							aa.appendChild(ad[Z].cloneNode(true))
						}
					}
				}
			}
		}
		return aa
	}
	function u(ai, ag, Y) {
		var X, aa = c(Y);
		if (M.wk && M.wk < 312) {
			return X
		}
		if (aa) {
			if (typeof ai.id == D) {
				ai.id = Y
			}
			if (M.ie && M.win) {
				var ah = "";
				for ( var ae in ai) {
					if (ai[ae] != Object.prototype[ae]) {
						if (ae.toLowerCase() == "data") {
							ag.movie = ai[ae]
						} else {
							if (ae.toLowerCase() == "styleclass") {
								ah += ' class="' + ai[ae] + '"'
							} else {
								if (ae.toLowerCase() != "classid") {
									ah += " " + ae + '="' + ai[ae] + '"'
								}
							}
						}
					}
				}
				var af = "";
				for ( var ad in ag) {
					if (ag[ad] != Object.prototype[ad]) {
						af += '<param name="' + ad + '" value="' + ag[ad]
								+ '" />'
					}
				}
				aa.outerHTML = '<object classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000"'
						+ ah + ">" + af + "</object>";
				N[N.length] = ai.id;
				X = c(ai.id)
			} else {
				var Z = C(r);
				Z.setAttribute("type", q);
				for ( var ac in ai) {
					if (ai[ac] != Object.prototype[ac]) {
						if (ac.toLowerCase() == "styleclass") {
							Z.setAttribute("class", ai[ac])
						} else {
							if (ac.toLowerCase() != "classid") {
								Z.setAttribute(ac, ai[ac])
							}
						}
					}
				}
				for ( var ab in ag) {
					if (ag[ab] != Object.prototype[ab]
							&& ab.toLowerCase() != "movie") {
						e(Z, ab, ag[ab])
					}
				}
				aa.parentNode.replaceChild(Z, aa);
				X = Z
			}
		}
		return X
	}
	function e(Z, X, Y) {
		var aa = C("param");
		aa.setAttribute("name", X);
		aa.setAttribute("value", Y);
		Z.appendChild(aa)
	}
	function y(Y) {
		var X = c(Y);
		if (X && X.nodeName == "OBJECT") {
			if (M.ie && M.win) {
				X.style.display = "none";
				(function() {
					if (X.readyState == 4) {
						b(Y)
					} else {
						setTimeout(arguments.callee, 10)
					}
				})()
			} else {
				X.parentNode.removeChild(X)
			}
		}
	}
	function b(Z) {
		var Y = c(Z);
		if (Y) {
			for ( var X in Y) {
				if (typeof Y[X] == "function") {
					Y[X] = null
				}
			}
			Y.parentNode.removeChild(Y)
		}
	}
	function c(Z) {
		var X = null;
		try {
			X = j.getElementById(Z)
		} catch (Y) {
		}
		return X
	}
	function C(X) {
		return j.createElement(X)
	}
	function i(Z, X, Y) {
		Z.attachEvent(X, Y);
		I[I.length] = [ Z, X, Y ]
	}
	function F(Z) {
		var Y = M.pv, X = Z.split(".");
		X[0] = parseInt(X[0], 10);
		X[1] = parseInt(X[1], 10) || 0;
		X[2] = parseInt(X[2], 10) || 0;
		return (Y[0] > X[0] || (Y[0] == X[0] && Y[1] > X[1]) || (Y[0] == X[0]
				&& Y[1] == X[1] && Y[2] >= X[2])) ? true : false
	}
	function v(ac, Y, ad, ab) {
		if (M.ie && M.mac) {
			return
		}
		var aa = j.getElementsByTagName("head")[0];
		if (!aa) {
			return
		}
		var X = (ad && typeof ad == "string") ? ad : "screen";
		if (ab) {
			n = null;
			G = null
		}
		if (!n || G != X) {
			var Z = C("style");
			Z.setAttribute("type", "text/css");
			Z.setAttribute("media", X);
			n = aa.appendChild(Z);
			if (M.ie && M.win && typeof j.styleSheets != D
					&& j.styleSheets.length > 0) {
				n = j.styleSheets[j.styleSheets.length - 1]
			}
			G = X
		}
		if (M.ie && M.win) {
			if (n && typeof n.addRule == r) {
				n.addRule(ac, Y)
			}
		} else {
			if (n && typeof j.createTextNode != D) {
				n.appendChild(j.createTextNode(ac + " {" + Y + "}"))
			}
		}
	}
	function w(Z, X) {
		if (!m) {
			return
		}
		var Y = X ? "visible" : "hidden";
		if (J && c(Z)) {
			c(Z).style.visibility = Y
		} else {
			v("#" + Z, "visibility:" + Y)
		}
	}
	function L(Y) {
		var Z = /[\\\"<>\.;]/;
		var X = Z.exec(Y) != null;
		return X && typeof encodeURIComponent != D ? encodeURIComponent(Y) : Y
	}
	var d = function() {
		if (M.ie && M.win) {
			window.attachEvent("onunload", function() {
				var ac = I.length;
				for ( var ab = 0; ab < ac; ab++) {
					I[ab][0].detachEvent(I[ab][1], I[ab][2])
				}
				var Z = N.length;
				for ( var aa = 0; aa < Z; aa++) {
					y(N[aa])
				}
				for ( var Y in M) {
					M[Y] = null
				}
				M = null;
				for ( var X in swfobject) {
					swfobject[X] = null
				}
				swfobject = null
			})
		}
	}();
	return {
		registerObject : function(ab, X, aa, Z) {
			if (M.w3 && ab && X) {
				var Y = {};
				Y.id = ab;
				Y.swfVersion = X;
				Y.expressInstall = aa;
				Y.callbackFn = Z;
				o[o.length] = Y;
				w(ab, false)
			} else {
				if (Z) {
					Z( {
						success : false,
						id : ab
					})
				}
			}
		},
		getObjectById : function(X) {
			if (M.w3) {
				return z(X)
			}
		},
		embedSWF : function(ab, ah, ae, ag, Y, aa, Z, ad, af, ac) {
			var X = {
				success : false,
				id : ah
			};
			if (M.w3 && !(M.wk && M.wk < 312) && ab && ah && ae && ag && Y) {
				w(ah, false);
				K(function() {
					ae += "";
					ag += "";
					var aj = {};
					if (af && typeof af === r) {
						for ( var al in af) {
							aj[al] = af[al]
						}
					}
					aj.data = ab;
					aj.width = ae;
					aj.height = ag;
					var am = {};
					if (ad && typeof ad === r) {
						for ( var ak in ad) {
							am[ak] = ad[ak]
						}
					}
					if (Z && typeof Z === r) {
						for ( var ai in Z) {
							if (typeof am.flashvars != D) {
								am.flashvars += "&" + ai + "=" + Z[ai]
							} else {
								am.flashvars = ai + "=" + Z[ai]
							}
						}
					}
					if (F(Y)) {
						var an = u(aj, am, ah);
						if (aj.id == ah) {
							w(ah, true)
						}
						X.success = true;
						X.ref = an
					} else {
						if (aa && A()) {
							aj.data = aa;
							P(aj, am, ah, ac);
							return
						} else {
							w(ah, true)
						}
					}
					if (ac) {
						ac(X)
					}
				})
			} else {
				if (ac) {
					ac(X)
				}
			}
		},
		switchOffAutoHideShow : function() {
			m = false
		},
		ua : M,
		getFlashPlayerVersion : function() {
			return {
				major : M.pv[0],
				minor : M.pv[1],
				release : M.pv[2]
			}
		},
		hasFlashPlayerVersion : F,
		createSWF : function(Z, Y, X) {
			if (M.w3) {
				return u(Z, Y, X)
			} else {
				return undefined
			}
		},
		showExpressInstall : function(Z, aa, X, Y) {
			if (M.w3 && A()) {
				P(Z, aa, X, Y)
			}
		},
		removeSWF : function(X) {
			if (M.w3) {
				y(X)
			}
		},
		createCSS : function(aa, Z, Y, X) {
			if (M.w3) {
				v(aa, Z, Y, X)
			}
		},
		addDomLoadEvent : K,
		addLoadEvent : s,
		getQueryParamValue : function(aa) {
			var Z = j.location.search || j.location.hash;
			if (Z) {
				if (/\?/.test(Z)) {
					Z = Z.split("?")[1]
				}
				if (aa == null) {
					return L(Z)
				}
				var Y = Z.split("&");
				for ( var X = 0; X < Y.length; X++) {
					if (Y[X].substring(0, Y[X].indexOf("=")) == aa) {
						return L(Y[X].substring((Y[X].indexOf("=") + 1)))
					}
				}
			}
			return ""
		},
		expressInstallCallback : function() {
			if (a) {
				var X = c(R);
				if (X && l) {
					X.parentNode.replaceChild(l, X);
					if (Q) {
						w(Q, true);
						if (M.ie && M.win) {
							l.style.display = "block"
						}
					}
					if (E) {
						E(B)
					}
				}
				a = false
			}
		}
	}
}();
if (jQuery) {
	(function(a) {
		a
				.extend(
						a.fn,
						{
							uploadify : function(b) {
								a(this)
										.each(
												function() {
													settings = a
															.extend(
																	{
																		id : a(
																				this)
																				.attr(
																						"id"),
																		uploader : "uploadify.swf",
																		script : "uploadify.php",
																		expressInstall : null,
																		folder : "",
																		height : 30,
																		width : 110,
																		cancelImg : "cancel.png",
																		wmode : "opaque",
																		scriptAccess : "sameDomain",
																		fileDataName : "Filedata",
																		method : "POST",
																		queueSizeLimit : 999,
																		simUploadLimit : 1,
																		queueID : false,
																		displayData : "percentage",
																		onInit : function() {
																		},
																		onSelect : function() {
																		},
																		onQueueFull : function() {
																		},
																		onCheck : function() {
																		},
																		onCancel : function() {
																		},
																		onError : function() {
																		},
																		onProgress : function() {
																		},
																		onComplete : function() {
																		},
																		onAllComplete : function() {
																		}
																	}, b);
													var e = location.pathname;
													e = e.split("/");
													e.pop();
													e = e.join("/") + "/";
													var f = {};
													f.uploadifyID = settings.id;
													f.pagepath = e;
													if (settings.buttonImg) {
														f.buttonImg = escape(settings.buttonImg)
													}
													if (settings.buttonText) {
														f.buttonText = escape(settings.buttonText)
													}
													if (settings.rollover) {
														f.rollover = true
													}
													f.script = settings.script;
													f.folder = escape(settings.folder);
													if (settings.scriptData) {
														var g = "";
														for ( var d in settings.scriptData) {
															g += "&"
																	+ d
																	+ "="
																	+ settings.scriptData[d]
														}
														f.scriptData = escape(g
																.substr(1))
													}
													f.width = settings.width;
													f.height = settings.height;
													f.wmode = settings.wmode;
													f.method = settings.method;
													f.queueSizeLimit = settings.queueSizeLimit;
													f.simUploadLimit = settings.simUploadLimit;
													if (settings.hideButton) {
														f.hideButton = true
													}
													if (settings.fileDesc) {
														f.fileDesc = settings.fileDesc
													}
													if (settings.fileExt) {
														f.fileExt = settings.fileExt
													}
													if (settings.multi) {
														f.multi = true
													}
													if (settings.auto) {
														f.auto = true
													}
													if (settings.sizeLimit) {
														f.sizeLimit = settings.sizeLimit
													}
													if (settings.checkScript) {
														f.checkScript = settings.checkScript
													}
													if (settings.fileDataName) {
														f.fileDataName = settings.fileDataName
													}
													if (settings.queueID) {
														f.queueID = settings.queueID
													}
													if (settings.onInit() !== false) {
														a(this).css("display",
																"none");
														a(this)
																.after(
																		'<div id="' + a(
																				this)
																				.attr(
																						"id") + 'Uploader"></div>');
														swfobject
																.embedSWF(
																		settings.uploader,
																		settings.id
																				+ "Uploader",
																		settings.width,
																		settings.height,
																		"9.0.24",
																		settings.expressInstall,
																		f,
																		{
																			quality : "high",
																			wmode : settings.wmode,
																			allowScriptAccess : settings.scriptAccess
																		});
														if (settings.queueID == false) {
															a(
																	"#"
																			+ a(
																					this)
																					.attr(
																							"id")
																			+ "Uploader")
																	.after(
																			'<div id="' + a(
																					this)
																					.attr(
																							"id") + 'Queue" class="uploadifyQueue"></div>')
														}
													}
													if (typeof (settings.onOpen) == "function") {
														a(this)
																.bind(
																		"uploadifyOpen",
																		settings.onOpen)
													}
													a(this)
															.bind(
																	"uploadifySelect",
																	{
																		action : settings.onSelect,
																		queueID : settings.queueID
																	},
																	function(j,
																			h,
																			i) {
																		if (j.data
																				.action(
																						j,
																						h,
																						i) !== false) {
																			var k = Math
																					.round(i.size / 1024 * 100) * 0.01;
																			var l = "KB";
																			if (k > 1000) {
																				k = Math
																						.round(k * 0.001 * 100) * 0.01;
																				l = "MB"
																			}
																			var m = k
																					.toString()
																					.split(
																							".");
																			if (m.length > 1) {
																				k = m[0]
																						+ "."
																						+ m[1]
																								.substr(
																										0,
																										2)
																			} else {
																				k = m[0]
																			}
																			if (i.name.length > 20) {
																				fileName = i.name
																						.substr(
																								0,
																								20)
																						+ "..."
																			} else {
																				fileName = i.name
																			}
																			queue = "#"
																					+ a(
																							this)
																							.attr(
																									"id")
																					+ "Queue";
																			if (j.data.queueID) {
																				queue = "#"
																						+ j.data.queueID
																			}
																			a(
																					queue)
																					.append(
																							'<div id="'
																									+ a(
																											this)
																											.attr(
																													"id")
																									+ h
																									+ '" class="uploadifyQueueItem"><div class="cancel"><a href="javascript:jQuery(\'#'
																									+ a(
																											this)
																											.attr(
																													"id")
																									+ "').uploadifyCancel('"
																									+ h
																									+ '\')"><img src="'
																									+ settings.cancelImg
																									+ '" border="0" /></a></div><span class="fileName">'
																									+ fileName
																									+ " ("
																									+ k
																									+ l
																									+ ')</span><span class="percentage"></span><div class="uploadifyProgress"><div id="'
																									+ a(
																											this)
																											.attr(
																													"id")
																									+ h
																									+ 'ProgressBar" class="uploadifyProgressBar"><!--Progress Bar--></div></div></div>')
																		}
																	});
													if (typeof (settings.onSelectOnce) == "function") {
														a(this)
																.bind(
																		"uploadifySelectOnce",
																		settings.onSelectOnce)
													}
													a(this)
															.bind(
																	"uploadifyQueueFull",
																	{
																		action : settings.onQueueFull
																	},
																	function(h,
																			i) {
																		if (h.data
																				.action(
																						h,
																						i) !== false) {
																			alert("The queue is full.  The max size is "
																					+ i
																					+ ".")
																		}
																	});
													a(this)
															.bind(
																	"uploadifyCheckExist",
																	{
																		action : settings.onCheck
																	},
																	function(m,
																			l,
																			k,
																			j,
																			o) {
																		var i = new Object();
																		i = k;
																		i.folder = e
																				+ j;
																		if (o) {
																			for ( var h in k) {
																				var n = h
																			}
																		}
																		a
																				.post(
																						l,
																						i,
																						function(
																								r) {
																							for ( var p in r) {
																								if (m.data
																										.action(
																												m,
																												l,
																												k,
																												j,
																												o) !== false) {
																									var q = confirm("Do you want to replace the file "
																											+ r[p]
																											+ "?");
																									if (!q) {
																										document
																												.getElementById(
																														a(
																																m.target)
																																.attr(
																																		"id")
																																+ "Uploader")
																												.cancelFileUpload(
																														p,
																														true,
																														true)
																									}
																								}
																							}
																							if (o) {
																								document
																										.getElementById(
																												a(
																														m.target)
																														.attr(
																																"id")
																														+ "Uploader")
																										.startFileUpload(
																												n,
																												true)
																							} else {
																								document
																										.getElementById(
																												a(
																														m.target)
																														.attr(
																																"id")
																														+ "Uploader")
																										.startFileUpload(
																												null,
																												true)
																							}
																						},
																						"json")
																	});
													a(this)
															.bind(
																	"uploadifyCancel",
																	{
																		action : settings.onCancel
																	},
																	function(l,
																			h,
																			k,
																			m,
																			j) {
																		if (l.data
																				.action(
																						l,
																						h,
																						k,
																						m,
																						j) !== false) {
																			var i = (j == true) ? 0
																					: 250;
																			a(
																					"#"
																							+ a(
																									this)
																									.attr(
																											"id")
																							+ h)
																					.fadeOut(
																							i,
																							function() {
																								a(
																										this)
																										.remove()
																							})
																		}
																	});
													if (typeof (settings.onClearQueue) == "function") {
														a(this)
																.bind(
																		"uploadifyClearQueue",
																		settings.onClearQueue)
													}
													var c = [];
													a(this)
															.bind(
																	"uploadifyError",
																	{
																		action : settings.onError
																	},
																	function(l,
																			h,
																			k,
																			j) {
																		if (l.data
																				.action(
																						l,
																						h,
																						k,
																						j) !== false) {
																			var i = new Array(
																					h,
																					k,
																					j);
																			c
																					.push(i);
																			a(
																					"#"
																							+ a(
																									this)
																									.attr(
																											"id")
																							+ h
																							+ " .percentage")
																					.text(
																							" - "
																									+ j.type
																									+ " Error");
																			a(
																					"#"
																							+ a(
																									this)
																									.attr(
																											"id")
																							+ h)
																					.addClass(
																							"uploadifyError")
																		}
																	});
													a(this)
															.bind(
																	"uploadifyProgress",
																	{
																		action : settings.onProgress,
																		toDisplay : settings.displayData
																	},
																	function(j,
																			h,
																			i,
																			k) {
																		if (j.data
																				.action(
																						j,
																						h,
																						i,
																						k) !== false) {
																			a(
																					"#"
																							+ a(
																									this)
																									.attr(
																											"id")
																							+ h
																							+ "ProgressBar")
																					.css(
																							"width",
																							k.percentage
																									+ "%");
																			if (j.data.toDisplay == "percentage") {
																				displayData = " - "
																						+ k.percentage
																						+ "%"
																			}
																			if (j.data.toDisplay == "speed") {
																				displayData = " - "
																						+ k.speed
																						+ "KB/s"
																			}
																			if (j.data.toDisplay == null) {
																				displayData = " "
																			}
																			a(
																					"#"
																							+ a(
																									this)
																									.attr(
																											"id")
																							+ h
																							+ " .percentage")
																					.text(
																							displayData)
																		}
																	});
													a(this)
															.bind(
																	"uploadifyComplete",
																	{
																		action : settings.onComplete
																	},
																	function(k,
																			h,
																			j,
																			i,
																			l) {
																		if (k.data
																				.action(
																						k,
																						h,
																						j,
																						unescape(i),
																						l) !== false) {
																			a(
																					"#"
																							+ a(
																									this)
																									.attr(
																											"id")
																							+ h
																							+ " .percentage")
																					.text(
																							" - Completed");
																			a(
																					"#"
																							+ a(
																									this)
																									.attr(
																											"id")
																							+ h)
																					.fadeOut(
																							250,
																							function() {
																								a(
																										this)
																										.remove()
																							})
																		}
																	});
													if (typeof (settings.onAllComplete) == "function") {
														a(this)
																.bind(
																		"uploadifyAllComplete",
																		{
																			action : settings.onAllComplete
																		},
																		function(
																				h,
																				i) {
																			if (h.data
																					.action(
																							h,
																							i) !== false) {
																				c = []
																			}
																		})
													}
												})
							},
							uploadifySettings : function(f, j, c) {
								var g = false;
								a(this)
										.each(
												function() {
													if (f == "scriptData"
															&& j != null) {
														if (c) {
															var i = j
														} else {
															var i = a
																	.extend(
																			settings.scriptData,
																			j)
														}
														var l = "";
														for ( var k in i) {
															l += "&"
																	+ k
																	+ "="
																	+ escape(i[k])
														}
														j = l.substr(1)
													}
													g = document
															.getElementById(
																	a(this)
																			.attr(
																					"id")
																			+ "Uploader")
															.updateSettings(f,
																	j)
												});
								if (j == null) {
									if (f == "scriptData") {
										var b = unescape(g).split("&");
										var e = new Object();
										for ( var d = 0; d < b.length; d++) {
											var h = b[d].split("=");
											e[h[0]] = h[1]
										}
										g = e
									}
									return g
								}
							},
							uploadifyUpload : function(b) {
								a(this).each(
										function() {
											document.getElementById(
													a(this).attr("id")
															+ "Uploader")
													.startFileUpload(b, false)
										})
							},
							uploadifyCancel : function(b) {
								a(this).each(
										function() {
											document.getElementById(
													a(this).attr("id")
															+ "Uploader")
													.cancelFileUpload(b, true,
															false)
										})
							},
							uploadifyClearQueue : function() {
								a(this)
										.each(
												function() {
													document
															.getElementById(
																	a(this)
																			.attr(
																					"id")
																			+ "Uploader")
															.clearFileUploadQueue(
																	false)
												})
							}
						})
	})(jQuery)
};
