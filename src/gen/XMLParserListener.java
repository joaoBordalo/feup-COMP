package gen;

// Generated from C:/Users/Pedro Faria/IdeaProjects/feup-COMP/src\XMLParser.g4 by ANTLR 4.5
import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link XMLParser}.
 */
public interface XMLParserListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link XMLParser#document}.
	 * @param ctx the parse tree
	 */
	void enterDocument(@NotNull XMLParser.DocumentContext ctx);
	/**
	 * Exit a parse tree produced by {@link XMLParser#document}.
	 * @param ctx the parse tree
	 */
	void exitDocument(@NotNull XMLParser.DocumentContext ctx);
	/**
	 * Enter a parse tree produced by {@link XMLParser#xmlItems}.
	 * @param ctx the parse tree
	 */
	void enterXmlItems(@NotNull XMLParser.XmlItemsContext ctx);
	/**
	 * Exit a parse tree produced by {@link XMLParser#xmlItems}.
	 * @param ctx the parse tree
	 */
	void exitXmlItems(@NotNull XMLParser.XmlItemsContext ctx);
	/**
	 * Enter a parse tree produced by {@link XMLParser#attributeName}.
	 * @param ctx the parse tree
	 */
	void enterAttributeName(@NotNull XMLParser.AttributeNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link XMLParser#attributeName}.
	 * @param ctx the parse tree
	 */
	void exitAttributeName(@NotNull XMLParser.AttributeNameContext ctx);
	/**
	 * Enter a parse tree produced by {@link XMLParser#attribute}.
	 * @param ctx the parse tree
	 */
	void enterAttribute(@NotNull XMLParser.AttributeContext ctx);
	/**
	 * Exit a parse tree produced by {@link XMLParser#attribute}.
	 * @param ctx the parse tree
	 */
	void exitAttribute(@NotNull XMLParser.AttributeContext ctx);
	/**
	 * Enter a parse tree produced by {@link XMLParser#attributeValue}.
	 * @param ctx the parse tree
	 */
	void enterAttributeValue(@NotNull XMLParser.AttributeValueContext ctx);
	/**
	 * Exit a parse tree produced by {@link XMLParser#attributeValue}.
	 * @param ctx the parse tree
	 */
	void exitAttributeValue(@NotNull XMLParser.AttributeValueContext ctx);
	/**
	 * Enter a parse tree produced by {@link XMLParser#airportElement}.
	 * @param ctx the parse tree
	 */
	void enterAirportElement(@NotNull XMLParser.AirportElementContext ctx);
	/**
	 * Exit a parse tree produced by {@link XMLParser#airportElement}.
	 * @param ctx the parse tree
	 */
	void exitAirportElement(@NotNull XMLParser.AirportElementContext ctx);
	/**
	 * Enter a parse tree produced by {@link XMLParser#airportElements}.
	 * @param ctx the parse tree
	 */
	void enterAirportElements(@NotNull XMLParser.AirportElementsContext ctx);
	/**
	 * Exit a parse tree produced by {@link XMLParser#airportElements}.
	 * @param ctx the parse tree
	 */
	void exitAirportElements(@NotNull XMLParser.AirportElementsContext ctx);
	/**
	 * Enter a parse tree produced by {@link XMLParser#comment}.
	 * @param ctx the parse tree
	 */
	void enterComment(@NotNull XMLParser.CommentContext ctx);
	/**
	 * Exit a parse tree produced by {@link XMLParser#comment}.
	 * @param ctx the parse tree
	 */
	void exitComment(@NotNull XMLParser.CommentContext ctx);
	/**
	 * Enter a parse tree produced by {@link XMLParser#servicesElement}.
	 * @param ctx the parse tree
	 */
	void enterServicesElement(@NotNull XMLParser.ServicesElementContext ctx);
	/**
	 * Exit a parse tree produced by {@link XMLParser#servicesElement}.
	 * @param ctx the parse tree
	 */
	void exitServicesElement(@NotNull XMLParser.ServicesElementContext ctx);
	/**
	 * Enter a parse tree produced by {@link XMLParser#servicesElements}.
	 * @param ctx the parse tree
	 */
	void enterServicesElements(@NotNull XMLParser.ServicesElementsContext ctx);
	/**
	 * Exit a parse tree produced by {@link XMLParser#servicesElements}.
	 * @param ctx the parse tree
	 */
	void exitServicesElements(@NotNull XMLParser.ServicesElementsContext ctx);
	/**
	 * Enter a parse tree produced by {@link XMLParser#fuelElement}.
	 * @param ctx the parse tree
	 */
	void enterFuelElement(@NotNull XMLParser.FuelElementContext ctx);
	/**
	 * Exit a parse tree produced by {@link XMLParser#fuelElement}.
	 * @param ctx the parse tree
	 */
	void exitFuelElement(@NotNull XMLParser.FuelElementContext ctx);
	/**
	 * Enter a parse tree produced by {@link XMLParser#towerElement}.
	 * @param ctx the parse tree
	 */
	void enterTowerElement(@NotNull XMLParser.TowerElementContext ctx);
	/**
	 * Exit a parse tree produced by {@link XMLParser#towerElement}.
	 * @param ctx the parse tree
	 */
	void exitTowerElement(@NotNull XMLParser.TowerElementContext ctx);
	/**
	 * Enter a parse tree produced by {@link XMLParser#runwayElement}.
	 * @param ctx the parse tree
	 */
	void enterRunwayElement(@NotNull XMLParser.RunwayElementContext ctx);
	/**
	 * Exit a parse tree produced by {@link XMLParser#runwayElement}.
	 * @param ctx the parse tree
	 */
	void exitRunwayElement(@NotNull XMLParser.RunwayElementContext ctx);
	/**
	 * Enter a parse tree produced by {@link XMLParser#runwayElements}.
	 * @param ctx the parse tree
	 */
	void enterRunwayElements(@NotNull XMLParser.RunwayElementsContext ctx);
	/**
	 * Exit a parse tree produced by {@link XMLParser#runwayElements}.
	 * @param ctx the parse tree
	 */
	void exitRunwayElements(@NotNull XMLParser.RunwayElementsContext ctx);
	/**
	 * Enter a parse tree produced by {@link XMLParser#markingsElement}.
	 * @param ctx the parse tree
	 */
	void enterMarkingsElement(@NotNull XMLParser.MarkingsElementContext ctx);
	/**
	 * Exit a parse tree produced by {@link XMLParser#markingsElement}.
	 * @param ctx the parse tree
	 */
	void exitMarkingsElement(@NotNull XMLParser.MarkingsElementContext ctx);
	/**
	 * Enter a parse tree produced by {@link XMLParser#lightsElement}.
	 * @param ctx the parse tree
	 */
	void enterLightsElement(@NotNull XMLParser.LightsElementContext ctx);
	/**
	 * Exit a parse tree produced by {@link XMLParser#lightsElement}.
	 * @param ctx the parse tree
	 */
	void exitLightsElement(@NotNull XMLParser.LightsElementContext ctx);
	/**
	 * Enter a parse tree produced by {@link XMLParser#offsetThresholdElement}.
	 * @param ctx the parse tree
	 */
	void enterOffsetThresholdElement(@NotNull XMLParser.OffsetThresholdElementContext ctx);
	/**
	 * Exit a parse tree produced by {@link XMLParser#offsetThresholdElement}.
	 * @param ctx the parse tree
	 */
	void exitOffsetThresholdElement(@NotNull XMLParser.OffsetThresholdElementContext ctx);
	/**
	 * Enter a parse tree produced by {@link XMLParser#approachLightsElement}.
	 * @param ctx the parse tree
	 */
	void enterApproachLightsElement(@NotNull XMLParser.ApproachLightsElementContext ctx);
	/**
	 * Exit a parse tree produced by {@link XMLParser#approachLightsElement}.
	 * @param ctx the parse tree
	 */
	void exitApproachLightsElement(@NotNull XMLParser.ApproachLightsElementContext ctx);
	/**
	 * Enter a parse tree produced by {@link XMLParser#vasiElement}.
	 * @param ctx the parse tree
	 */
	void enterVasiElement(@NotNull XMLParser.VasiElementContext ctx);
	/**
	 * Exit a parse tree produced by {@link XMLParser#vasiElement}.
	 * @param ctx the parse tree
	 */
	void exitVasiElement(@NotNull XMLParser.VasiElementContext ctx);
	/**
	 * Enter a parse tree produced by {@link XMLParser#ilsElement}.
	 * @param ctx the parse tree
	 */
	void enterIlsElement(@NotNull XMLParser.IlsElementContext ctx);
	/**
	 * Exit a parse tree produced by {@link XMLParser#ilsElement}.
	 * @param ctx the parse tree
	 */
	void exitIlsElement(@NotNull XMLParser.IlsElementContext ctx);
	/**
	 * Enter a parse tree produced by {@link XMLParser#ilsElements}.
	 * @param ctx the parse tree
	 */
	void enterIlsElements(@NotNull XMLParser.IlsElementsContext ctx);
	/**
	 * Exit a parse tree produced by {@link XMLParser#ilsElements}.
	 * @param ctx the parse tree
	 */
	void exitIlsElements(@NotNull XMLParser.IlsElementsContext ctx);
	/**
	 * Enter a parse tree produced by {@link XMLParser#glideSlopeElement}.
	 * @param ctx the parse tree
	 */
	void enterGlideSlopeElement(@NotNull XMLParser.GlideSlopeElementContext ctx);
	/**
	 * Exit a parse tree produced by {@link XMLParser#glideSlopeElement}.
	 * @param ctx the parse tree
	 */
	void exitGlideSlopeElement(@NotNull XMLParser.GlideSlopeElementContext ctx);
	/**
	 * Enter a parse tree produced by {@link XMLParser#dmeElement}.
	 * @param ctx the parse tree
	 */
	void enterDmeElement(@NotNull XMLParser.DmeElementContext ctx);
	/**
	 * Exit a parse tree produced by {@link XMLParser#dmeElement}.
	 * @param ctx the parse tree
	 */
	void exitDmeElement(@NotNull XMLParser.DmeElementContext ctx);
	/**
	 * Enter a parse tree produced by {@link XMLParser#startElement}.
	 * @param ctx the parse tree
	 */
	void enterStartElement(@NotNull XMLParser.StartElementContext ctx);
	/**
	 * Exit a parse tree produced by {@link XMLParser#startElement}.
	 * @param ctx the parse tree
	 */
	void exitStartElement(@NotNull XMLParser.StartElementContext ctx);
	/**
	 * Enter a parse tree produced by {@link XMLParser#comElement}.
	 * @param ctx the parse tree
	 */
	void enterComElement(@NotNull XMLParser.ComElementContext ctx);
	/**
	 * Exit a parse tree produced by {@link XMLParser#comElement}.
	 * @param ctx the parse tree
	 */
	void exitComElement(@NotNull XMLParser.ComElementContext ctx);
	/**
	 * Enter a parse tree produced by {@link XMLParser#runwayAliasElement}.
	 * @param ctx the parse tree
	 */
	void enterRunwayAliasElement(@NotNull XMLParser.RunwayAliasElementContext ctx);
	/**
	 * Exit a parse tree produced by {@link XMLParser#runwayAliasElement}.
	 * @param ctx the parse tree
	 */
	void exitRunwayAliasElement(@NotNull XMLParser.RunwayAliasElementContext ctx);
	/**
	 * Enter a parse tree produced by {@link XMLParser#waypointElement}.
	 * @param ctx the parse tree
	 */
	void enterWaypointElement(@NotNull XMLParser.WaypointElementContext ctx);
	/**
	 * Exit a parse tree produced by {@link XMLParser#waypointElement}.
	 * @param ctx the parse tree
	 */
	void exitWaypointElement(@NotNull XMLParser.WaypointElementContext ctx);
	/**
	 * Enter a parse tree produced by {@link XMLParser#routeElement}.
	 * @param ctx the parse tree
	 */
	void enterRouteElement(@NotNull XMLParser.RouteElementContext ctx);
	/**
	 * Exit a parse tree produced by {@link XMLParser#routeElement}.
	 * @param ctx the parse tree
	 */
	void exitRouteElement(@NotNull XMLParser.RouteElementContext ctx);
	/**
	 * Enter a parse tree produced by {@link XMLParser#routeElements}.
	 * @param ctx the parse tree
	 */
	void enterRouteElements(@NotNull XMLParser.RouteElementsContext ctx);
	/**
	 * Exit a parse tree produced by {@link XMLParser#routeElements}.
	 * @param ctx the parse tree
	 */
	void exitRouteElements(@NotNull XMLParser.RouteElementsContext ctx);
	/**
	 * Enter a parse tree produced by {@link XMLParser#nextElement}.
	 * @param ctx the parse tree
	 */
	void enterNextElement(@NotNull XMLParser.NextElementContext ctx);
	/**
	 * Exit a parse tree produced by {@link XMLParser#nextElement}.
	 * @param ctx the parse tree
	 */
	void exitNextElement(@NotNull XMLParser.NextElementContext ctx);
	/**
	 * Enter a parse tree produced by {@link XMLParser#previousElement}.
	 * @param ctx the parse tree
	 */
	void enterPreviousElement(@NotNull XMLParser.PreviousElementContext ctx);
	/**
	 * Exit a parse tree produced by {@link XMLParser#previousElement}.
	 * @param ctx the parse tree
	 */
	void exitPreviousElement(@NotNull XMLParser.PreviousElementContext ctx);
	/**
	 * Enter a parse tree produced by {@link XMLParser#approachElement}.
	 * @param ctx the parse tree
	 */
	void enterApproachElement(@NotNull XMLParser.ApproachElementContext ctx);
	/**
	 * Exit a parse tree produced by {@link XMLParser#approachElement}.
	 * @param ctx the parse tree
	 */
	void exitApproachElement(@NotNull XMLParser.ApproachElementContext ctx);
	/**
	 * Enter a parse tree produced by {@link XMLParser#approachElements}.
	 * @param ctx the parse tree
	 */
	void enterApproachElements(@NotNull XMLParser.ApproachElementsContext ctx);
	/**
	 * Exit a parse tree produced by {@link XMLParser#approachElements}.
	 * @param ctx the parse tree
	 */
	void exitApproachElements(@NotNull XMLParser.ApproachElementsContext ctx);
	/**
	 * Enter a parse tree produced by {@link XMLParser#approachLegsElement}.
	 * @param ctx the parse tree
	 */
	void enterApproachLegsElement(@NotNull XMLParser.ApproachLegsElementContext ctx);
	/**
	 * Exit a parse tree produced by {@link XMLParser#approachLegsElement}.
	 * @param ctx the parse tree
	 */
	void exitApproachLegsElement(@NotNull XMLParser.ApproachLegsElementContext ctx);
	/**
	 * Enter a parse tree produced by {@link XMLParser#missedApproachLegsElement}.
	 * @param ctx the parse tree
	 */
	void enterMissedApproachLegsElement(@NotNull XMLParser.MissedApproachLegsElementContext ctx);
	/**
	 * Exit a parse tree produced by {@link XMLParser#missedApproachLegsElement}.
	 * @param ctx the parse tree
	 */
	void exitMissedApproachLegsElement(@NotNull XMLParser.MissedApproachLegsElementContext ctx);
	/**
	 * Enter a parse tree produced by {@link XMLParser#transitionElement}.
	 * @param ctx the parse tree
	 */
	void enterTransitionElement(@NotNull XMLParser.TransitionElementContext ctx);
	/**
	 * Exit a parse tree produced by {@link XMLParser#transitionElement}.
	 * @param ctx the parse tree
	 */
	void exitTransitionElement(@NotNull XMLParser.TransitionElementContext ctx);
	/**
	 * Enter a parse tree produced by {@link XMLParser#transitionLegsElement}.
	 * @param ctx the parse tree
	 */
	void enterTransitionLegsElement(@NotNull XMLParser.TransitionLegsElementContext ctx);
	/**
	 * Exit a parse tree produced by {@link XMLParser#transitionLegsElement}.
	 * @param ctx the parse tree
	 */
	void exitTransitionLegsElement(@NotNull XMLParser.TransitionLegsElementContext ctx);
	/**
	 * Enter a parse tree produced by {@link XMLParser#legElement}.
	 * @param ctx the parse tree
	 */
	void enterLegElement(@NotNull XMLParser.LegElementContext ctx);
	/**
	 * Exit a parse tree produced by {@link XMLParser#legElement}.
	 * @param ctx the parse tree
	 */
	void exitLegElement(@NotNull XMLParser.LegElementContext ctx);
	/**
	 * Enter a parse tree produced by {@link XMLParser#helipadElement}.
	 * @param ctx the parse tree
	 */
	void enterHelipadElement(@NotNull XMLParser.HelipadElementContext ctx);
	/**
	 * Exit a parse tree produced by {@link XMLParser#helipadElement}.
	 * @param ctx the parse tree
	 */
	void exitHelipadElement(@NotNull XMLParser.HelipadElementContext ctx);
	/**
	 * Enter a parse tree produced by {@link XMLParser#jetwayElement}.
	 * @param ctx the parse tree
	 */
	void enterJetwayElement(@NotNull XMLParser.JetwayElementContext ctx);
	/**
	 * Exit a parse tree produced by {@link XMLParser#jetwayElement}.
	 * @param ctx the parse tree
	 */
	void exitJetwayElement(@NotNull XMLParser.JetwayElementContext ctx);
	/**
	 * Enter a parse tree produced by {@link XMLParser#sceneryObjectElement}.
	 * @param ctx the parse tree
	 */
	void enterSceneryObjectElement(@NotNull XMLParser.SceneryObjectElementContext ctx);
	/**
	 * Exit a parse tree produced by {@link XMLParser#sceneryObjectElement}.
	 * @param ctx the parse tree
	 */
	void exitSceneryObjectElement(@NotNull XMLParser.SceneryObjectElementContext ctx);
	/**
	 * Enter a parse tree produced by {@link XMLParser#sceneryObjectElements}.
	 * @param ctx the parse tree
	 */
	void enterSceneryObjectElements(@NotNull XMLParser.SceneryObjectElementsContext ctx);
	/**
	 * Exit a parse tree produced by {@link XMLParser#sceneryObjectElements}.
	 * @param ctx the parse tree
	 */
	void exitSceneryObjectElements(@NotNull XMLParser.SceneryObjectElementsContext ctx);
	/**
	 * Enter a parse tree produced by {@link XMLParser#libraryObject}.
	 * @param ctx the parse tree
	 */
	void enterLibraryObject(@NotNull XMLParser.LibraryObjectContext ctx);
	/**
	 * Exit a parse tree produced by {@link XMLParser#libraryObject}.
	 * @param ctx the parse tree
	 */
	void exitLibraryObject(@NotNull XMLParser.LibraryObjectContext ctx);
	/**
	 * Enter a parse tree produced by {@link XMLParser#taxiwayPointElement}.
	 * @param ctx the parse tree
	 */
	void enterTaxiwayPointElement(@NotNull XMLParser.TaxiwayPointElementContext ctx);
	/**
	 * Exit a parse tree produced by {@link XMLParser#taxiwayPointElement}.
	 * @param ctx the parse tree
	 */
	void exitTaxiwayPointElement(@NotNull XMLParser.TaxiwayPointElementContext ctx);
	/**
	 * Enter a parse tree produced by {@link XMLParser#taxiwayParkingElement}.
	 * @param ctx the parse tree
	 */
	void enterTaxiwayParkingElement(@NotNull XMLParser.TaxiwayParkingElementContext ctx);
	/**
	 * Exit a parse tree produced by {@link XMLParser#taxiwayParkingElement}.
	 * @param ctx the parse tree
	 */
	void exitTaxiwayParkingElement(@NotNull XMLParser.TaxiwayParkingElementContext ctx);
	/**
	 * Enter a parse tree produced by {@link XMLParser#taxiNameElement}.
	 * @param ctx the parse tree
	 */
	void enterTaxiNameElement(@NotNull XMLParser.TaxiNameElementContext ctx);
	/**
	 * Exit a parse tree produced by {@link XMLParser#taxiNameElement}.
	 * @param ctx the parse tree
	 */
	void exitTaxiNameElement(@NotNull XMLParser.TaxiNameElementContext ctx);
	/**
	 * Enter a parse tree produced by {@link XMLParser#taxiwayPathElement}.
	 * @param ctx the parse tree
	 */
	void enterTaxiwayPathElement(@NotNull XMLParser.TaxiwayPathElementContext ctx);
	/**
	 * Exit a parse tree produced by {@link XMLParser#taxiwayPathElement}.
	 * @param ctx the parse tree
	 */
	void exitTaxiwayPathElement(@NotNull XMLParser.TaxiwayPathElementContext ctx);
	/**
	 * Enter a parse tree produced by {@link XMLParser#startpoint}.
	 * @param ctx the parse tree
	 */
	void enterStartpoint(@NotNull XMLParser.StartpointContext ctx);
	/**
	 * Exit a parse tree produced by {@link XMLParser#startpoint}.
	 * @param ctx the parse tree
	 */
	void exitStartpoint(@NotNull XMLParser.StartpointContext ctx);
}