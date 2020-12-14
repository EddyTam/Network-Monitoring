package {
	import flash.display.DisplayObjectContainer;
	import flash.display.DisplayObject;
	import flash.display.MovieClip;
	import flash.display.Stage;
	import org.libspark.betweenas3.BetweenAS3;
	import org.libspark.betweenas3.tweens.ITween;
	import org.libspark.betweenas3.events.TweenEvent;
	import org.libspark.betweenas3.easing.Expo;
	import org.libspark.betweenas3.easing.Sine;

	public class dropshadow {

		private var _root:MovieClip;
		private var _targetmc:DisplayObject;
		private var _distance:Number;
		private var _angle:Number;
		private var _color:uint;
		private var _alpha:Number;
		private var _blurX:Number;
		private var _blurY:Number;
		private var _strength:Number;
		private var _quality:Number;
		
		//
		//CONSTRUCTOR FOR DROPSHADOW
		//
		
		function dropshadow(r:MovieClip, tmc:DisplayObject, di:Number, an:Number, co:Number, al:Number, bx:Number, by:Number, str:Number, qua:Number) {
			_root = r;
			_targetmc = tmc;
			_distance = di;
			_angle = an;
			_color = co;
			_alpha = al;
			_blurX = bx;
			_blurY = by;
			_strength = str;
			_quality = qua;
			
			BetweenAS3.apply(_targetmc, {_dropShadowFilter:{distance:_distance, angle:_angle, color:_color, alpha:_alpha, blurX:_blurX, blurY:_blurY, strength:_strength, quality:_quality}});
		}
	}
}