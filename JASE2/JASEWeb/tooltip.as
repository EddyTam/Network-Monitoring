package {
	import flash.display.DisplayObjectContainer;
	import flash.display.MovieClip;
	import flash.display.Stage;
	import flash.display.StageAlign;
	import flash.display.StageScaleMode;
	import flash.display.Sprite;
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

	public class tooltip extends MovieClip{

		private var _root:MovieClip;
		private var _socialmc:MovieClip;
		private var _container:MovieClip;
		private var _ivar:Number;
		private var t_over:ITween;
		private var t_out:ITween;
		private var thisyvar:Number;

		//
		//CONSTRUCTOR FOR SOCIAL BUTTONS TOOLTIPS
		//
		
		function tooltip(r:MovieClip, soc:MovieClip, cont:MovieClip, iv:Number) {
			_root = r;
			_ivar = iv;
			_container = cont;
			_socialmc = soc;
			
			var myFormat:TextFormat = new TextFormat();
			var indent:Number = 5;
			myFormat.font = _root.textfont;
			myFormat.indent = indent;
			myFormat.color = _root.socialtextcolor;
			myFormat.size = _root.socialtextsize;
			
			var txt:TextField = new TextField();
			txt.multiline = false;
			txt.autoSize = TextFieldAutoSize.LEFT;
			txt.embedFonts = true;
			txt.selectable = false;
			txt.antiAliasType = AntiAliasType.ADVANCED;
			txt.text = _root.socialdata_array[_ivar]["text"];
			txt.setTextFormat(myFormat);
			txt.x = Math.round(-(txt.width/2)-(indent/2));
			txt.y = -Math.round((txt.height/2));
			txt.mouseEnabled = false;
			var txt_shadow:dropshadow = new dropshadow(_root, txt, 1, 90, _root.socialtextshadowcolor, _root.socialtextshadowalpha, _root.socialtextshadowblur, _root.socialtextshadowblur, 1, 1);
	
			var square:Sprite = new Sprite();
			square.graphics.beginFill(_root.socialbgcolor);
			square.graphics.drawRoundRect(-Math.round((txt.width+indent)/2), -Math.round((txt.height+indent)/2), Math.round(txt.width+indent), Math.round(txt.height+indent), _root.socialbgrounded, _root.socialbgrounded);
			square.graphics.endFill();
			square.mouseEnabled = false;
			
			this.mouseEnabled = false;
			this.addChild(square);
			this.addChild(txt);
			this.alpha = 0;
			thisyvar = Math.round(_socialmc.y-(this.height/2)-5);
			this.y = thisyvar;
			this.x = _socialmc.x+Math.round(this.width/2);
			_container.addChild(this);
			
			_socialmc.addEventListener(MouseEvent.MOUSE_OVER, mouse_over,false,0,true);
			_socialmc.addEventListener(MouseEvent.MOUSE_OUT, mouse_out,false,0,true);
		}
		
		//
		//MOUSE INTERACTION FUNCTIONS
		//
		
		private function mouse_over(event):void {
			this.alpha=0;
			this.y = thisyvar+5;
			t_over = BetweenAS3.to(this,{alpha:1, y:thisyvar},0.4,Expo.easeOut);
			if (t_out!=null) {
				t_out.stop();
			}
			t_over.play();
		}
		
		private function mouse_out(event):void {
			t_out = BetweenAS3.to(this,{alpha:0, y:thisyvar+5},0.4,Expo.easeOut);
			if (t_over!=null) {
				t_over.stop();
			}
			t_out.play();
		}
	}
}