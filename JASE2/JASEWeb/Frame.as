package {
	import flash.display.Bitmap;
	import flash.display.BitmapData;
	import flash.display.Loader;
	import flash.display.StageAlign;
	import flash.display.StageScaleMode;
	import flash.display.MovieClip;
	import flash.geom.ColorTransform;
	import flash.utils.Timer;
	import flash.events.Event;
	import flash.events.MouseEvent;
	import flash.events.TimerEvent;
	import flash.net.URLLoader;
	import flash.net.URLRequest;
	import flash.system.LoaderContext;
	import flash.text.StyleSheet;
	import flash.text.TextField;
	import flash.text.TextFormat;
	import flash.filters.BlurFilter;
	import flash.filters.BitmapFilter;
	import flash.filters.BitmapFilterQuality;
	import flash.events.IOErrorEvent;

	public class template extends MovieClip {

		public var rootvar:MovieClip;
		public var activeimage:String;
		public var firstrun:Boolean = true;
		
		public var bg:Object;
		public var footer:Object;
		public var module:Object;
		public var util:Object;

		public var fsvar_stage:Boolean = false;
		
		public var headerheight:int;
		
		//
		//GENERAL TEMPLATE OPTIONS VARIABLES
		//

		public var websitetitle:String;
		public var headerminheight:int;
		public var headermaxheight:int;
		public var templatewidth:int;
		public var templateheight:int;
		public var menuareawidth:int;
		public var logourl:String;
		public var logoorientation:String;
		public var logoheight:int;
		public var logopadding:int;
		public var logoclicklink:String;
		public var logoclicktarget:String;
		public var menufont:String;
		public var textfont:String;
		public var menuleftpadding:int;
		public var menufontsize:int;
		public var menuspacing:int;
		public var menuidlecolor:uint;
		public var menuselectcolor:uint;
		public var menushadowstrength:int;
		public var menushadowopacity:Number;
		public var menushadowdistance:int;
		public var submenufontsize:int;
		public var submenuindent:int;
		public var submenuspacing:int;
		public var submenuidlecolor:uint;
		public var submenuselectcolor:uint;
		public var submenushadowstrength:int;
		public var submenushadowopacity:Number;
		public var submenushadowdistance:int;
		public var bggradientcolor1:uint;
		public var bggradientcolor2:uint;
		public var bggradientratio1:int;
		public var bggradientratio2:int;
		public var bgimagealpha:Number;
		public var bggradientalpha:Number;
		public var footerheight:int;
		public var footerbgopacity:Number;
		public var footerbgcolor:uint;
		public var footerpixelopacity:Number;
		public var footerpixelcolor:uint;
		public var footershadowdistance:int;
		public var footershadowstrength:int;
		public var footershadowopacity:Number;
		public var footertextshadowdistance:int;
		public var footertextshadowstrength:int;
		public var footertextshadowopacity:Number;
		public var musicfullscreenrightpadding:int;
		public var musicfullscreenbottompadding:int;
		public var musicfullscreencolor:uint;
		public var musicfullscreentextsize:int;
		public var musicfullscreenopacity:Number;
		public var musicplayerlabel:String;
		public var fullscreenlabel:String;
		public var assetshadowopacity:Number;
		public var assetshadowdistance:int;
		public var assetshadowstrength:int;
		public var assetshadowblur:int;
		public var assetshadowcolor:uint;
		
		//
		//SOCIAL OPTIONS
		//
		
		public var socialleftpadding:int;
		public var socialbottompadding:int;
		public var socialbgcolor:uint;
		public var socialbgrounded:int;
		public var socialtextcolor:uint;
		public var socialtextsize:int;
		public var socialtextshadowalpha:Number;
		public var socialtextshadowblur:int;
		public var socialtextshadowcolor:uint;
		public var sociallogosize:int;
		public var sociallogospacing:int;
		
		//
		//MUSIC OPTIONS
		//
		
		public var musicactivated:String;
		//public var musicautoplay:String;
		public var musicvolumecolor:uint;
		public var musicvolumebgcolor:uint;
		public var musiciconcolor:uint;
		public var musicbuttoncolor:uint;
		public var musicstartvolume:Number;
		
		public var musicplayerwidth:int;
		public var musicplayerpadding:int;
		public var musicvolumeheight:int;
		public var musicentryheight:int;
		public var musicentryfontsize:int;
		public var musicentryfontcolor:uint;
		public var musicentrybgcolor:uint;
		public var musicentrybgopacity:Number;
		
		//
		//FOOTER TEXT
		//
		
		public var footertext:String;
		
		//
		//DATA VARIABLES
		//
		
		private var optionsxmlfile:String;
		private var menuxmlfile:String;
		private var cssfile:String;
		
		private var optionsloaded:Boolean = false;
		private var menuloaded:Boolean = false;
		private var cssloaded:Boolean = false;
	
		private var optionsxmldata:XML;
		private var menuxmldata:XML;
		
		public var socialdata_array:Array = new Array();
		public var musicdata_array:Array = new Array();
		public var menudata_array:Array = new Array();
		
		public var sociallength:int;
		public var musiclength:int;
		public var mainmenulength:int;
		
		private var cssLoader:URLLoader = new URLLoader();
		public var style:StyleSheet = new StyleSheet();
		private var xmlOptionsLoader:URLLoader = new URLLoader();
		private var xmlMenuLoader:URLLoader = new URLLoader();

		function template() {
			
			rootvar = this;
			
			//
			//STAGE SETTINGS
			//
			
			stage.align = "TL";
			stage.scaleMode = "noScale";
			
			//
			//XML AND CSS PATHS
			//
			
			optionsxmlfile = "xml/options.xml";
			menuxmlfile = "xml/menu.xml";
			cssfile = "xml/styles.css";
			
			//
			//CSS LOADING
			//

			cssLoader.addEventListener(IOErrorEvent.IO_ERROR, function(evt:IOErrorEvent):void {},false,0,true);
			cssLoader.addEventListener(Event.COMPLETE, cssLoadingDone);
			cssLoader.load(new URLRequest(cssfile));
			
			//
			//OPTIONS XML LOADING
			//

			xmlOptionsLoader.addEventListener(IOErrorEvent.IO_ERROR, function(evt:IOErrorEvent):void {},false,0,true);
			xmlOptionsLoader.addEventListener(Event.COMPLETE, xmlOptionsLoadingDone);
			xmlOptionsLoader.load(new URLRequest(optionsxmlfile));
			
			//
			//MENU XML LOADING
			//

			xmlMenuLoader.addEventListener(IOErrorEvent.IO_ERROR, function(evt:IOErrorEvent):void {},false,0,true);
			xmlMenuLoader.addEventListener(Event.COMPLETE, xmlMenuLoadingDone);
			xmlMenuLoader.load(new URLRequest(menuxmlfile));
			
			//
			//CHECK IF EVERYTHING IS LOADED
			//
			
			addEventListener(Event.ENTER_FRAME, loadCheck);
			
			function loadCheck(e:Event):void {
				if(cssloaded && optionsloaded && menuloaded){
					removeEventListener(Event.ENTER_FRAME, loadCheck);
					initTemplate();
				}
			}
			
			//
			//CSS DATA PARSING
			//
			
			function cssLoadingDone(e:Event):void {
				cssLoader.removeEventListener(Event.COMPLETE, cssLoadingDone);
				style.parseCSS(cssLoader.data);
				cssloaded = true;
			}

			//
			//OPTIONS XML DATA PARSING
			//

			function xmlOptionsLoadingDone(e:Event):void {
				xmlOptionsLoader.removeEventListener(Event.COMPLETE, xmlOptionsLoadingDone);

				optionsxmldata = new XML(e.currentTarget.data);

				//
				//GENERAL OPTIONS
				//
				
				websitetitle = optionsxmldata.general.attribute("websitetitle");
				headerminheight = optionsxmldata.general.attribute("headerminheight");
				headermaxheight = optionsxmldata.general.attribute("headermaxheight");
				templatewidth = optionsxmldata.general.attribute("templatewidth");
				templateheight = optionsxmldata.general.attribute("templateheight");
				menuareawidth = optionsxmldata.general.attribute("menuareawidth");
				logourl = optionsxmldata.general.attribute("logourl");
				logoorientation = optionsxmldata.general.attribute("logoorientation");
				logoheight = optionsxmldata.general.attribute("logoheight");
				logopadding = optionsxmldata.general.attribute("logopadding");
				logoclicklink = optionsxmldata.general.attribute("logoclicklink");
				logoclicktarget = optionsxmldata.general.attribute("logoclicktarget");
				menufont = optionsxmldata.general.attribute("menufont");
				textfont = optionsxmldata.general.attribute("textfont");
				menufontsize = optionsxmldata.general.attribute("menufontsize");
				menuleftpadding = optionsxmldata.general.attribute("menuleftpadding");
				menuspacing = optionsxmldata.general.attribute("menuspacing");
				menuidlecolor = optionsxmldata.general.attribute("menuidlecolor");
				menuselectcolor = optionsxmldata.general.attribute("menuselectcolor");
				menushadowstrength = optionsxmldata.general.attribute("menushadowstrength");
				menushadowopacity = parseFloat(optionsxmldata.general.attribute("menushadowopacity"));
				menushadowdistance = optionsxmldata.general.attribute("menushadowdistance");
				submenufontsize = optionsxmldata.general.attribute("submenufontsize");
				submenuindent = optionsxmldata.general.attribute("submenuindent");
				submenuspacing = optionsxmldata.general.attribute("submenuspacing");
				submenuidlecolor = optionsxmldata.general.attribute("submenuidlecolor");
				submenuselectcolor = optionsxmldata.general.attribute("submenuselectcolor");
				submenushadowstrength = optionsxmldata.general.attribute("submenushadowstrength");
				submenushadowopacity = parseFloat(optionsxmldata.general.attribute("submenushadowopacity"));
				submenushadowdistance = optionsxmldata.general.attribute("submenushadowdistance");
				bggradientcolor1 = optionsxmldata.general.attribute("bggradientcolor1");
				bggradientcolor2 = optionsxmldata.general.attribute("bggradientcolor2");
				bggradientratio1 = optionsxmldata.general.attribute("bggradientratio1");
				bggradientratio2 = optionsxmldata.general.attribute("bggradientratio2");
				bgimagealpha = parseFloat(optionsxmldata.general.attribute("bgimagealpha"));
				bggradientalpha = parseFloat(optionsxmldata.general.attribute("bggradientalpha"));
				footerheight = optionsxmldata.general.attribute("footerheight");
				footerbgopacity = parseFloat(optionsxmldata.general.attribute("footerbgopacity"));
				footerbgcolor = optionsxmldata.general.attribute("footerbgcolor");
				footerpixelopacity = parseFloat(optionsxmldata.general.attribute("footerpixelopacity"));
				footerpixelcolor = optionsxmldata.general.attribute("footerpixelcolor");
				footershadowdistance = optionsxmldata.general.attribute("footershadowdistance");
				footershadowstrength = optionsxmldata.general.attribute("footershadowstrength");
				footershadowopacity = parseFloat(optionsxmldata.general.attribute("footershadowopacity"));
				footertextshadowdistance = optionsxmldata.general.attribute("footertextshadowdistance");
				footertextshadowstrength = optionsxmldata.general.attribute("footertextshadowstrength");
				footertextshadowopacity = parseFloat(optionsxmldata.general.attribute("footertextshadowopacity"));
				musicfullscreenrightpadding = optionsxmldata.general.attribute("musicfullscreenrightpadding");
				musicfullscreenbottompadding = optionsxmldata.general.attribute("musicfullscreenbottompadding");
				musicfullscreencolor = optionsxmldata.general.attribute("musicfullscreencolor");
				musicfullscreentextsize = optionsxmldata.general.attribute("musicfullscreentextsize");
				musicfullscreenopacity = parseFloat(optionsxmldata.general.attribute("musicfullscreenopacity"));
				musicplayerlabel = optionsxmldata.general.attribute("musicplayerlabel");
				fullscreenlabel = optionsxmldata.general.attribute("fullscreenlabel");
				assetshadowopacity = parseFloat(optionsxmldata.general.attribute("assetshadowopacity"));
				assetshadowdistance = optionsxmldata.general.attribute("assetshadowdistance");
				assetshadowstrength = optionsxmldata.general.attribute("assetshadowstrength");
				assetshadowblur = optionsxmldata.general.attribute("assetshadowblur");
				assetshadowcolor = optionsxmldata.general.attribute("assetshadowcolor");
				
				//
				//SOCIAL LINK ENTRIES
				//
				
				socialleftpadding = optionsxmldata.socialentries.attribute("socialleftpadding");
				socialbottompadding = optionsxmldata.socialentries.attribute("socialbottompadding");
				socialbgcolor = optionsxmldata.socialentries.attribute("socialbgcolor");
				socialbgrounded = optionsxmldata.socialentries.attribute("socialbgrounded");
				socialtextcolor = optionsxmldata.socialentries.attribute("socialtextcolor");
				socialtextsize = optionsxmldata.socialentries.attribute("socialtextsize");
				socialtextshadowalpha = parseFloat(optionsxmldata.socialentries.attribute("socialtextshadowalpha"));
				socialtextshadowblur = optionsxmldata.socialentries.attribute("socialtextshadowblur");
				socialtextshadowcolor = optionsxmldata.socialentries.attribute("socialtextshadowcolor");
				sociallogosize = optionsxmldata.socialentries.attribute("sociallogosize");
				sociallogospacing = optionsxmldata.socialentries.attribute("sociallogospacing");

				sociallength = optionsxmldata.socialentries.socialentry.length();
				
				for (var i:int = 0; i < sociallength; i++) {
					var socialdata:Object = new Object();
					
					socialdata["socialnumber"] = i;
					socialdata["iconlink"] = optionsxmldata.socialentries.socialentry.attribute("iconlink")[i];
					socialdata["urllink"] = optionsxmldata.socialentries.socialentry.attribute("urllink")[i];
					socialdata["urltarget"] = optionsxmldata.socialentries.socialentry.attribute("urltarget")[i];
					socialdata["text"] = optionsxmldata.socialentries.socialentry[i];
					
					socialdata_array[i] = socialdata;
				}
				
				//trace(socialdata_array[0]["text"]);
				
				//
				//MUSIC ENTRIES
				//
				
				musicactivated = optionsxmldata.musicentries.attribute("musicactivated");
				//musicautoplay = optionsxmldata.musicentries.attribute("musicautoplay");
				musicvolumecolor = optionsxmldata.musicentries.attribute("musicvolumecolor");
				musicvolumebgcolor = optionsxmldata.musicentries.attribute("musicvolumebgcolor");
				musiciconcolor = optionsxmldata.musicentries.attribute("musiciconcolor");
				musicbuttoncolor = optionsxmldata.musicentries.attribute("musicbuttoncolor");
				musicstartvolume = parseFloat(optionsxmldata.musicentries.attribute("musicstartvolume"));
		
				musicplayerwidth = optionsxmldata.musicentries.attribute("musicplayerwidth");
				musicplayerpadding = optionsxmldata.musicentries.attribute("musicplayerpadding");
				musicvolumeheight = optionsxmldata.musicentries.attribute("musicvolumeheight");
				musicentryheight = optionsxmldata.musicentries.attribute("musicentryheight");
				musicentryfontsize = optionsxmldata.musicentries.attribute("musicentryfontsize");
				musicentryfontcolor = optionsxmldata.musicentries.attribute("musicentryfontcolor");
				musicentrybgcolor = optionsxmldata.musicentries.attribute("musicentrybgcolor");
				musicentrybgopacity = parseFloat(optionsxmldata.musicentries.attribute("musicentrybgopacity"));
				
				musiclength = optionsxmldata.musicentries.song.length();
				
				for (var j:int = 0; j < musiclength; j++) {
					var musicdata:Object = new Object();
					
					musicdata["tracknumber"] = j;
					musicdata["urllink"] = optionsxmldata.musicentries.song.attribute("urllink")[j];
					musicdata["text"] = optionsxmldata.musicentries.song[j];
					
					musicdata_array[j] = musicdata;
				}
				
				//
				//FOOTER TEXT
				//
				
				footertext = optionsxmldata.footertext;
				
				optionsloaded = true;
			}
			
			//
			//MENU XML DATA PARSING
			//

			function xmlMenuLoadingDone(e:Event):void {
				xmlMenuLoader.removeEventListener(Event.COMPLETE, xmlMenuLoadingDone);

				menuxmldata = new XML(e.currentTarget.data);
				
				//
				//MENU XML DATA
				//

				mainmenulength = menuxmldata.mainmenu.length();

				for (var k:int = 0; k < mainmenulength; k++) {
					var menudata:Object = new Object();

					menudata["mmnumber"] = k;
					menudata["headline"] = menuxmldata.mainmenu.attribute("headline")[k];
					menudata["module"] = menuxmldata.mainmenu.attribute("module")[k];
					menudata["modulexml"] = menuxmldata.mainmenu.attribute("modulexml")[k];
					menudata["modulewidth"] = menuxmldata.mainmenu.attribute("modulewidth")[k];
					menudata["moduleheight"] = menuxmldata.mainmenu.attribute("moduleheight")[k];
					menudata["backgroundimage"] = menuxmldata.mainmenu.attribute("backgroundimage")[k];
					menudata["urllink"] = menuxmldata.mainmenu.attribute("urllink")[k];
					menudata["urltarget"] = menuxmldata.mainmenu.attribute("urltarget")[k];
					if(menuxmldata.mainmenu[k].submenu.length()>0){
						menudata["smamount"] = menuxmldata.mainmenu[k].submenu.length();
					}else{
						menudata["smamount"] = 0;
					}
					
					menudata["smnumber"] = {};
					menudata["smheadline"] = {};
					menudata["smmodule"] = {};
					menudata["smmodulexml"] = {};
					menudata["smmodulewidth"] = {};
					menudata["smmoduleheight"] = {};
					menudata["smbackgroundimage"] = {};
					menudata["smurllink"] = {};
					menudata["smurltarget"] = {};

					for (var j:int = 0; j < menuxmldata.mainmenu[k].submenu.length(); j++) {
						menudata["smnumber"][j] = j;
						menudata["smheadline"][j] = menuxmldata.mainmenu[k].submenu.attribute("headline")[j];
						menudata["smmodule"][j] = menuxmldata.mainmenu[k].submenu.attribute("module")[j];
						menudata["smmodulexml"][j] = menuxmldata.mainmenu[k].submenu.attribute("modulexml")[j];
						menudata["smmodulewidth"][j] = menuxmldata.mainmenu[k].submenu.attribute("modulewidth")[j];
						menudata["smmoduleheight"][j] = menuxmldata.mainmenu[k].submenu.attribute("moduleheight")[j];
						menudata["smbackgroundimage"][j] = menuxmldata.mainmenu[k].submenu.attribute("backgroundimage")[j];
						menudata["smurllink"][j] = menuxmldata.mainmenu[k].submenu.attribute("urllink")[j];
						menudata["smurltarget"][j] = menuxmldata.mainmenu[k].submenu.attribute("urltarget")[j];
					}
					menudata_array[k] = menudata;
				}

				menuloaded = true;
			}
		}
		
		//
		//TEMPLATE INITIALISATION
		//
		
		private function initTemplate():void {
			
			bg = new createbg(stage, rootvar);
			footer = new createfooter(stage, rootvar);
			addChild(new createlogo(stage, rootvar));
			addChild(new createsocial(stage, rootvar));
			util = new createutil(stage, rootvar);
			module = new createmodule(stage, rootvar);
			addChild(new createmenu(stage, rootvar));
			
		}
	}
}