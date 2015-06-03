package org.osm2world.viewer.view;

import java.awt.Color;
import java.io.IOException;
import java.util.Arrays;

import javax.media.opengl.GL2ES2;
import javax.media.opengl.GL3;

import org.osm2world.core.math.Vector3D;

import com.jogamp.graph.curve.opengl.RenderState;
import com.jogamp.graph.curve.opengl.TextRenderer;
import com.jogamp.graph.font.Font;
import com.jogamp.graph.font.FontFactory;
import com.jogamp.graph.font.FontSet;
import com.jogamp.graph.geom.opengl.SVertex;
import com.jogamp.opengl.util.glsl.ShaderState;

public class TextRendererShader implements org.osm2world.viewer.view.TextRenderer {
	private RenderState renderState = RenderState.createRenderState(new ShaderState(), SVertex.factory());
	private TextRenderer textRenderer = TextRenderer.create(renderState, 0);
	private Font textRendererFont = null;
	private GL2ES2 gl;
	
	public TextRendererShader(GL2ES2 gl) {
		this.gl = gl;
		try {
			textRendererFont = FontFactory.getDefault().get(FontSet.FAMILY_REGULAR, FontSet.STYLE_SERIF);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		textRenderer.init(gl);
	}

//	@Override
//	public void drawText(String string, Vector3D pos, Color color) {
//		textRenderer.resetModelview(gl);
//		textRenderer.setColorStatic(gl, color.getRed(), color.getGreen(), color.getBlue());
//		float[] posF = {(float)pos.getX(), (float)pos.getY(), -(float)pos.getZ()};
//		int[] texSize = {0};
//		textRenderer.drawString3D(gl.getGL2ES2(), textRendererFont, string, posF, 12, texSize);
//	}

	@Override
	public void drawText(String string, int x, int y, int screenWidth,
			int screenHeight, Color color) {
		textRenderer.setColorStatic(gl, color.getRed(), color.getGreen(), color.getBlue());
		textRenderer.resetModelview(gl);
		textRenderer.reshapeOrtho(gl, screenWidth, screenHeight, -100000, 100000);
		textRenderer.translate(gl, x, y, 0);
		float[] posF = {0, 0, 0}; // not used in TextRendererImpl01
		int[] texSize = {0};
		textRenderer.drawString3D(gl, textRendererFont, string, posF, 12, texSize);
	}

}
