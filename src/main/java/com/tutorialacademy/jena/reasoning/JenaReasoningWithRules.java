package com.tutorialacademy.jena.reasoning;

import com.hp.hpl.jena.rdf.model.InfModel;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.Statement;
import com.hp.hpl.jena.rdf.model.StmtIterator;
import com.hp.hpl.jena.reasoner.Reasoner;
import com.hp.hpl.jena.reasoner.rulesys.GenericRuleReasoner;
import com.hp.hpl.jena.reasoner.rulesys.Rule;

public class JenaReasoningWithRules
{

	public static void main(String[] args) 
	{
		Model model = ModelFactory.createDefaultModel();
		model.read( "dataset.n3" );
		
		Reasoner reasoner = new GenericRuleReasoner( Rule.rulesFromURL( "rules.txt" ) );
		
		InfModel infModel = ModelFactory.createInfModel( reasoner, model );

		// print out the statements in the model
		StmtIterator it = infModel.listStatements();
		
		while ( it.hasNext() )
		{
			Statement stmt = it.nextStatement();
			
			Resource subject = stmt.getSubject();
			Property predicate = stmt.getPredicate();
			RDFNode object = stmt.getObject();

			System.out.println( subject.toString() + " " + predicate.toString() + " " + object.toString() );
		}
	}
}
