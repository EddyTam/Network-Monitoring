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

	public class submenu extends MovieClip{

		private var _root:MovieClip;
		private var _menu:MovieClip;
		private var _mainmenu:MovieClip;
		public var _ivar:int;
		private var _mainmenuivar:int;
		
		public var _menumc:MovieClip = new MovieClip();
		
		private var t_color:ITween;
		private var t_fade:ITween;
		public var t_move:ITween;
		private var colorTransform:ColorTransform = new ColorTransform();
		
		private var idlergb:Object;
		private var selectrgb:Object;
		
		private var txt:TextField = new TextField();
		private var myFormat:TextFormat = new TextFormat();
		
		public var deeplink:String;
		public var imagepath:String;
		public var modulepath:String;
		public var modulexmlpath:String;
		public var modulewidth:int;
		public var moduleheight:int;
		public var clicklink:String;
		public var clicktarget:String;
		
		//
		//CONSTRUCTOR FOR SUB MENU ENTRY
		//
		
		function submenu(r:MovieClip, men:MovieClip, mai:MovieClip, iv:int, miv:int) {
			_root = r;
			_menu = men;
			_mainmenu = mai;
			_ivar = iv;
			_mainmenuivar = miv;
			
			idlergb = _menu.makeRGB(_root.submenuidlecolor);
			selectrgb = _menu.makeRGB(_root.submenuselectcolor);

			myFormat.font = _root.menufont;
			myFormat.indent = _root.submenuindent;
			myFormat.color = _root.submenuidlecolor;
			myFormat.size = _root.submenufontsize;

			txt.multiline = false;
			txt.autoSize = TextFieldAutoSize.LEFT;
			txt.embedFonts = true;
			txt.selectable = false;
			txt.antiAliasType = AntiAliasType.ADVANCED;
			txt.text = _root.menudata_array[_mainmenuivar]["smheadline"][_ivar];
			txt.setTextFormat(myFormat);
			txt.mouseEnabled = false;
			colorTransform.color = _root.submenuidlecolor;
			txt.transform.colorTransform = colorTransform;
			
			deeplink = _root.menudata_array[_mainmenuivar]["smheadline"][_ivar].split(" ").join("_").split("/").join("_").toLowerCase();
			imagepath = _root.menudata_array[_mainmenuivar]["smbackgroundimage"][_ivar];
			modulepath = _root.menudata_array[_mainmenuivar]["smmodule"][_ivar];
			modulexmlpath = _root.menudata_array[_mainmenuivar]["smmodulexml"][_ivar];
			modulewidth = _root.menudata_array[_mainmenuivar]["smmodulewidth"][_ivar];
			moduleheight = _root.menudata_array[_mainmenuivar]["smmoduleheight"][_ivar];
			clicklink = _root.menudata_array[_mainmenuivar]["smurllink"][_ivar];
			clicktarget = _root.menudata_array[_mainmenuivar]["smurltarget"][_ivar];

			_menumc.alpha = 0;
			_menumc.mouseEnabled = false;
			_menumc.buttonMode = false;
			_menumc.visible = false;
			_menumc.addChild(txt);
			_menumc.y = _mainmenu.submenuy-Math.abs(Math.round(_root.submenuspacing/2));
			var txt_shadow:dropshadow = new dropshadow(_root, _menumc, _root.submenushadowdistance, 45, 0x000000, _root.submenushadowopacity, _root.submenushadowstrength, _root.submenushadowstrength, 2, 1);
			
			_mainmenu.submenuy+=Math.round(txt.height)+_root.submenuspacing;
			_mainmenu.submenuheight+=Math.round(txt.height)+_root.submenuspacing;
			this.addChild(_menumc);
			
			_mainmenu.submenu_array.push(this);
			
			addEvents();
		}
		
		private function addEvents():void{
			_menumc.addEventListener(MouseEvent.MOUSE_OVER, mouse_over);
			_menumc.addEventListener(MouseEvent.MOUSE_OUT, mouse_out);
			_menumc.addEventListener(MouseEvent.CLICK, mouse_click);
		}
		
		public function fadeIn(j:int):void {
			_menumc.visible = true;
			_menumc.mouseEnabled = true;
			_menumc.buttonMode = true;
			if(t_fade!=null){
				t_fade.stop();
			}
			var f1:ITween = BetweenAS3.to(_menumc,{alpha:1},0.6,Sine.easeOut);
			var f2:ITween = BetweenAS3.delay(f1, j*0.1);
			t_fade = BetweenAS3.parallel(f1,f2);
			t_fade.play();
		}
		
		public function fadeOut(j:int):void {
			_menumc.mouseEnabled = false;
			_menumc.buttonMode = false;
			if(t_fade!=null){
				t_fade.stop();
			}
			var f1:ITween = BetweenAS3.to(_menumc,{alpha:0},0.2,Sine.easeOut);
			var f2:ITween = BetweenAS3.delay(f1, j*0.03);
			t_fade = BetweenAS3.parallel(f1,f2);
			t_fade.onComplete = disable;
			t_fade.play();
		}
		
		private function disable():void{
			_menumc.visible = false;
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