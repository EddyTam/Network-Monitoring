package {
	import flash.display.DisplayObjectContainer;
	import flash.display.MovieClip;
	import flash.display.Stage;
	import flash.display.StageAlign;
	import flash.display.StageScaleMode;
	import flash.display.Sprite;
	import flash.geom.ColorTransform;
	import flash.system.LoaderContext;
	import flash.text.TextField;
	import flash.text.TextFormat;
	import flash.text.TextFieldAutoSize;
	import flash.text.AntiAliasType;
	import flash.text.GridFitType;
	import flash.net.URLLoader;
	import flash.net.URLRequest;
	import flash.net.navigateToURL;
	import flash.display.Loader;
	import flash.events.Event;
	import flash.events.MouseEvent;
	import org.libspark.betweenas3.BetweenAS3;
	import org.libspark.betweenas3.tweens.ITween;
	import org.libspark.betweenas3.events.TweenEvent;
	import org.libspark.betweenas3.easing.Expo;
	import org.libspark.betweenas3.easing.Sine;

	public class mainmenu extends MovieClip{

		private var _root:MovieClip;
		private var _menu:MovieClip;
		public var _ivar:int;
		
		public var _menumc:MovieClip = new MovieClip();
		private var _menumcwrapper:MovieClip = new MovieClip();
		
		private var t_color:ITween;
		private var t_fade:ITween;
		public var t_move:ITween;
		private var colorTransform:ColorTransform = new ColorTransform();
		
		private var txt:TextField = new TextField();
		private var myFormat:TextFormat = new TextFormat();
		private var indent:int = 0;
		
		private var idlergb:Object;
		private var selectrgb:Object;
		
		public var submenu_array:Array = new Array();
		public var submenuy:int = 0;
		public var submenuheight:int = 0;
		public var menuysave:int;
		public var slidedown:Boolean = false;
		public var submenupresent:Boolean = false;
		
		public var deeplink:String;
		public var imagepath:String;
		public var modulepath:String;
		public var modulexmlpath:String;
		public var modulewidth:int;
		public var moduleheight:int;
		public var clicklink:String;
		public var clicktarget:String;
		
		//
		//CONSTRUCTOR FOR MAIN MENU ENTRY
		//
		
		function mainmenu(r:MovieClip, men:MovieClip, iv:int) {
			_root = r;
			_menu = men;
			_ivar = iv;
			
			idlergb = _menu.makeRGB(_root.menuidlecolor);
			selectrgb = _menu.makeRGB(_root.menuselectcolor);

			myFormat.font = _root.menufont;
			myFormat.indent = indent;
			myFormat.color = _root.menuidlecolor;
			myFormat.size = _root.menufontsize;

			txt.multiline = false;
			txt.autoSize = TextFieldAutoSize.LEFT;
			txt.embedFonts = true;
			txt.selectable = false;
			txt.antiAliasType = AntiAliasType.ADVANCED;
			txt.gridFitType = GridFitType.PIXEL;
			txt.text = _root.menudata_array[_ivar]["headline"];
			txt.setTextFormat(myFormat);
			txt.mouseEnabled = false;
			colorTransform.color = _root.menuidlecolor;
			txt.transform.colorTransform = colorTransform;
			
			deeplink = _root.menudata_array[_ivar]["headline"].split(" ").join("_").split("/").join("_").toLowerCase();
			imagepath = _root.menudata_array[_ivar]["backgroundimage"];
			modulepath = _root.menudata_array[_ivar]["module"];
			modulexmlpath = _root.menudata_array[_ivar]["modulexml"];
			modulewidth = _root.menudata_array[_ivar]["modulewidth"];
			moduleheight = _root.menudata_array[_ivar]["moduleheight"];
			clicklink = _root.menudata_array[_ivar]["urllink"];
			clicktarget = _root.menudata_array[_ivar]["urltarget"];
			
			_menumc.mouseEnabled = true;
			_menumc.buttonMode = true;
			_menumc.addChild(txt);
			var txt_shadow:dropshadow = new dropshadow(_root, _menumc, _root.menushadowdistance, 45, 0x000000, _root.menushadowopacity, _root.menushadowstrength, _root.menushadowstrength, 2, 1);
			
			_menumc.alpha = 0;
			_menumcwrapper.y = _menu.menuy;
			menuysave = _menumc.y;
			_menu.menuy+=Math.round(_menumc.height)+_root.menuspacing;
			this.addChild(_menumcwrapper);
			_menumcwrapper.addChild(_menumc);
			submenuy = Math.round(txt.height);
			
			BetweenAS3.apply(_menumc, {_blurFilter: {blurX: 10,blurY: 10, quality:1}});
			var t1:ITween = BetweenAS3.to(_menumc,{alpha:1},0.5,Sine.easeOut);
			var t2:ITween = BetweenAS3.delay(t1, (_ivar+1)*0.1);
			var t3:ITween = BetweenAS3.to(_menumc,{_blurFilter:{blurX: 0, blurY: 0, quality:1}},0.8,Sine.easeOut);
			var t4:ITween = BetweenAS3.delay(t3, (_ivar+1)*0.1);
			t_fade = BetweenAS3.parallel(t1,t2,t3,t4);
			t_fade.onComplete = isFadedIn;
			t_fade.play();
			
			_menu.mainmenu_array.push(this);
			
			addSubmenu();
			addEvents();
		}
		
		private function isFadedIn():void{
			_menu.mainmenufadedin++;
		}
		
		private function addSubmenu():void{
			if(_root.menudata_array[_ivar]["smamount"]!=0){
				submenupresent = true;
				for (var i:int = 0; i < _root.menudata_array[_ivar]["smamount"]; i++) {
					_menumcwrapper.addChild(new submenu(_root, _menu, this, i, _ivar));
				}
			}
		}
		
		private function addEvents():void{
			_menumc.addEventListener(MouseEvent.MOUSE_OVER, mouse_over);
			_menumc.addEventListener(MouseEvent.MOUSE_OUT, mouse_out);
			_menumc.addEventListener(MouseEvent.CLICK, mouse_click);
		}
		
		//
		//MOUSE INTERACTION FUNCTIONS
		//
		
		public function mouse_over(e:MouseEvent):void {
			if(_menu.selectedmenu != this){
				if(t_color!=null){
					t_color.stop();
				}
				t_color = BetweenAS3.to(txt, { transform:{colorTransform:{ redOffset:selectrgb["red"], greenOffset:selectrgb["green"], blueOffset:selectrgb["blue"] }}}, 0.4, Expo.easeOut);
				t_color.play();
			}
		}
		
		public function mouse_out(e:MouseEvent):void {
			if(_menu.selectedmenu != this){
				if(t_color!=null){
					t_color.stop();
				}
				t_color = BetweenAS3.to(txt, { transform:{colorTransform:{ redOffset:idlergb["red"], greenOffset:idlergb["green"], blueOffset:idlergb["blue"] }}}, 0.4, Expo.easeOut);
				t_color.play();
			}
		}
		
		public function mouse_out_f():void {
			if(t_color!=null){
				t_color.stop();
			}
			t_color = BetweenAS3.to(txt, { transform:{colorTransform:{ redOffset:idlergb["red"], greenOffset:idlergb["green"], blueOffset:idlergb["blue"] }}}, 0.4, Expo.easeOut);
			t_color.play();
		}

		public function mouse_click(e:MouseEvent):void {
			if(_menu.selectedmenu != this){
				if(clicklink==""){
					if(_menu.selectedmenu!=null){
						_menu.selectedmenu._menumc.mouseEnabled = true;
						_menu.selectedmenu.mouse_out_f();
					}
					_menu.selectedmenu = this;
					_menu.selectModule();
					_menumc.mouseEnabled = false;
					if(submenupresent){
						if(_menu.slideoutmenu!=this && _menu.slideoutmenu==null){
							_menu.slideoutmenu = this;
							_menu.slideDownMenu(this);
							_menu.showSubMenu(this);
						}else if(_menu.slideoutmenu!=this && _menu.slideoutmenu!=null){
							_menu.hideSubMenu();
							_menu.slideUpMenu(_menu.slideoutmenu);
							_menu.slideoutmenu = this;
							_menu.slideDownMenu(this);
							_menu.showSubMenu(this);
						}
					}else{
						if(_menu.slideoutmenu !=null){
							_menu.hideSubMenu();
							_menu.slideUpMenu(_menu.slideoutmenu);
							_menu.slideoutmenu = null;
						}
					}
				}else{
					try {
						navigateToURL(new URLRequest(clicklink), clicktarget);
					} catch (e:Error) {
					}
				}
			}
		}
	}
}