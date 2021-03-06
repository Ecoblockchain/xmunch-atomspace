package com.xmunch.atomspace.api;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.xmunch.atomspace.aux.AtomParams;
import com.xmunch.atomspace.aux.AtomType;
import com.xmunch.atomspace.aux.Globals;
import com.xmunch.atomspace.model.AtomSpace;
import com.xmunch.atomspace.model.Edge;

@RestController
public class EdgeController {

	@RequestMapping("/edge/create")
	public Edge createEdge(@RequestParam(value = "label") String label,
			@RequestParam(value = "from") String from,
			@RequestParam(value = "to") String to,
			@RequestParam(value = "params", defaultValue = "") String params) {

		AtomSpace atomSpace = AtomSpace.getInstance();
		HashMap<String, String> atomParams = new HashMap<String, String>();
		atomParams.put(AtomParams.FROM.get(), from);
		atomParams.put(AtomParams.TO.get(), to);
		atomParams.put(AtomParams.EDGE_LABEL.get(), label);
		atomParams.put(AtomParams.EDGE_PARAMS.get(),
				params.replace(Globals.COMMA.get(), Globals.SPACE.get()));
		return (Edge) atomSpace.createAtom(AtomType.EDGE.get(), atomParams);
	}
	
	@RequestMapping("/edges/out/get/")
	public ArrayList<Edge> getVertexOutEdges(
			@RequestParam(value = "label", defaultValue = "no-name") String vertexLabel) {
		AtomSpace atomSpace = AtomSpace.getInstance();
		return (ArrayList<Edge>) atomSpace.getOutEdgesByVertexLabel(vertexLabel);
	}

}
