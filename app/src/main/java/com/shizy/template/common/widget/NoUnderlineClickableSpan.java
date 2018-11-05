package com.shizy.template.common.widget;

import android.support.annotation.NonNull;
import android.text.TextPaint;
import android.text.style.ClickableSpan;

import com.shizy.template.R;
import com.shizy.template.common.utils.ResourcesUtil;

public abstract class NoUnderlineClickableSpan extends ClickableSpan {

	@Override
	public void updateDrawState(@NonNull TextPaint ds) {
		ds.setColor(ResourcesUtil.getColor(R.color.link));
		ds.setUnderlineText(false);
	}
}
