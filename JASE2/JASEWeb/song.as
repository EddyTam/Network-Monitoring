package {
	import flash.display.DisplayObjectContainer;
	import flash.display.MovieClip;
	import flash.display.Stage;
	import flash.display.StageAlign;
	import flash.display.StageScaleMode;
	import flash.display.GradientType;
	import flash.display.SpreadMethod;
	import flash.display.InterpolationMethod;
	import flash.display.Sprite;
	import flash.media.Sound;
	import flash.media.SoundChannel;
	import flash.media.SoundTransform;
	import flash.geom.Matrix;
	import flash.events.Event;
	import flash.events.EventDispatcher;
	import flash.events.MouseEvent;
	import flash.text.GridFitType;
	import flash.text.TextFormat;
	import flash.text.TextField;
	import flash.text.AntiAliasType;
	import flash.text.TextFieldAutoSize;
	import flash.net.URLLoader;
	import flash.net.URLRequest;
	import flash.utils.setTimeout;
	import flash.events.IOErrorEvent;
	import org.libspark.betweenas3.BetweenAS3;
	import org.libspark.betweenas3.tweens.ITween;
	import org.libspark.betweenas3.events.TweenEvent;
	import org.libspark.betweenas3.easing.Expo;
	import org.libspark.betweenas3.easing.Sine;

	public class song extends MovieClip{
		
		private var _root:MovieClip;
		private var _stage:Stage;
		private var _container:MovieClip;
		public var _ivar:Number;
		public var _yvar:Number;
		private var _creator:MovieClip;
		
		private var tformat:TextFormat = new TextFormat();
		private var txt:TextField = new TextField();
		private var bg:Sprite = new Sprite();
		private var indent:int;
		
		public var select:Boolean = false;
		
		private var t_over:ITween;
		private var t_out:ITween;
		
		//
		//CONSTRUCTOR
		//
		
		function song(r:MovieClip, st:Stage, con:MovieClip, iv:Number, yv:Number, cre:MovieClip) {
			_root = r;
			_stage = st;
			_container = con;
			_ivar = iv;
			_yvar = yv;
			_creator = cre;
			
			indent = 5;
			tformat.font = _root.textfont;
			tformat.color = _root.musicentryfontcolor;
			tformat.size = _root.musicentryfontsize;
			
			txt.embedFonts = true;
			txt.selectable = false;
			txt.antiAliasType = AntiAliasType.ADVANCED;
			txt.autoSize = TextFieldAutoSize.LEFT;
			txt.gridFitType = GridFitType.PIXEL;
			txt.text = _root.musicdata_array[_ivar]["text"];
			txt.setTextFormat(tformat);
			txt.x = indent;
			txt.mouseEnabled = false;

			bg = new Sprite();
			bg.graphics.beginFill(0x000000);
			bg.graphics.drawRect(0, 0, _root.musicplayerwidth, _root.musicentryheight);
			bg.graphics.endFill();
			
			txt.y = Math.round((bg.height/2)-(txt.height/2));
		
			this.mouseEnabled = true;
			this.buttonMode = true;
			this.alpha = _root.musicentrybgopacity;
			this.addChild(bg);
			this.addChild(txt);
			
			this.y = _yvar;
			_container.addChild(this);
			
			this.addEventListener(MouseEvent.MOUSE_OUT, onMouseOut, false, 0, true);
			this.addEventListener(MouseEvent.MOUSE_OVER, onMouseOver, false, 0, true);
			this.addEventListener(MouseEvent.CLICK, mouse_click,false,0,true);
		}
		
		private function mouse_click(event):void {
			if(!select){
				selectEntry();
			}
		}
		
		private function onMouseOut(event:MouseEvent) {
			if(!select){
				if (t_over!=null) {
					t_over.stop();
				}
				t_out = BetweenAS3.to(this, {alpha:_root.musicentrybgopacity},1,Expo.easeOut);
				t_out.play();
			}
		}

		private function onMouseOver(event:MouseEvent) {
			if(!select){
				if (t_out!=null) {
					t_out.stop();
				}
				t_over = BetweenAS3.to(this, {alpha:1},1,Expo.easeOut);
				t_over.play();
			}
		}
		
		public function selectEntry():void {
			if (t_out!=null) {
				t_out.stop();
			}
			t_over = BetweenAS3.to(this, {alpha:1},1,Expo.easeOut);
			t_over.play();
			if(_creator.activesong!=null){
				_creator.activesong.deselectEntry();
			}
			select = true;
			_creator.activesongid = _ivar;
			_creator.activesong = this;
			_creator.unPause();
			if(_creator.isplaying){
				_creator.stopSong();
			}
			_creator.playSong();
		}
		
		public function deselectEntry():void {
			if (t_over!=null) {
				t_over.stop();
			}
			t_out = BetweenAS3.to(this, {alpha:_root.musicentrybgopacity},1,Expo.easeOut);
			t_out.play();
			select = false;
		}
	}
}